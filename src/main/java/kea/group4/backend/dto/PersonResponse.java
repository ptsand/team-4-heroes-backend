package kea.group4.backend.dto;

import kea.group4.backend.entities.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonResponse {
    Long id;
    String username;
    List<String> roleNames;
    String email;
    String firstName;
    String lastName;
    Long phoneNumber;

    public PersonResponse(Person person) {
        this.id = person.getId();
        this.username = person.getUsername();
        this.roleNames = person.getRoles().stream().map(role -> role.toString()).collect(Collectors.toList());
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phoneNumber = person.getPhoneNumber();
    }

    public static List<PersonResponse> getPersonsFromEntities(List<Person> persons) {
        return persons.stream().map(PersonResponse::new).collect(Collectors.toList());
    }
}
