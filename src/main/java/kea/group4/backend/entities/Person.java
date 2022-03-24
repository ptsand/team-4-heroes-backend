package kea.group4.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kea.group4.backend.dto.PersonRequest;
import kea.group4.backend.security.UserWithPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Person implements UserWithPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private long phoneNumber; //TODO: Format phone number for print

    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;
    @JsonIgnore
    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private Set<HobbyInfo> hobbyInfos = new HashSet<HobbyInfo>();
    // Below attributes are required for the security part
    @Column(nullable = false, length = 50, unique = true)
    private String username;
    // 72 == Max length of a bcrypt encoded password
    // https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html
    @Column(nullable = false, length = 72)
    private String password;
    boolean enabled;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('USER','ADMIN')")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="user_role")
    List<Role> roles = new ArrayList<>();

    @Override
    public void addRole(Role role) {
        this.roles.add(role);
    }

    public Person(String email, String firstName, String lastName, long phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        // TODO: fix tests instead
        this.username = firstName;
        this.password = lastName;
    }

    public Person(String email, String firstName, String lastName, long phoneNumber, String username, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = pwEncoder.encode(password);
        this.enabled = true;
    }

    @Override
    public void setPassword(String password) {
        this.password = pwEncoder.encode(password);
    }

    public Person(PersonRequest body) {
        this.email = body.getEmail();
        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.phoneNumber = body.getPhoneNumber();
    }
}
