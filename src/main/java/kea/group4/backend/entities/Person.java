package kea.group4.backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private long phoneNumber; //TODO: Format phone number for print

    @OneToMany(fetch = FetchType.EAGER)
    private Set<HobbyInfo> hobbies = new HashSet<HobbyInfo>();

    public void addHobby(HobbyInfo hobby) {
        hobbies.add(hobby);
    }

    public Person(String email, String firstName, String lastName, long phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Person(PersonRequest body) {
        this.email = body.getEmail();
        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.phoneNumber = body.getPhoneNumber();
    }

}
