package kea.group4.backend.entities;

import kea.group4.backend.dto.PersonRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private long phoneNumber;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;
    //@OneToMany
    //private Set<HobbyInfo> hobbies = new HashSet<HobbyInfo>();
    //TODO: Format phone number for print

    //public void addHobby(HobbyInfo hobby) {
        //hobbies.add(hobby);
    //}

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
