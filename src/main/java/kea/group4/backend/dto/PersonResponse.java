package kea.group4.backend.dto;

import kea.group4.backend.entities.HobbyInfo;
import kea.group4.backend.entities.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PersonResponse {
    long id;
    String email;
    String firstName;
    String lastName;
    //TODO: format phone number to xx xx xx xx
    long phoneNumber;
    Set<HobbyInfo> hobbyInfos;

    public PersonResponse(Person person) {
        this.id = person.getId();
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.hobbyInfos = person.getHobbyInfos();
    }

    public static List<PersonResponse> getPersonsFromEntities(List<Person> persons) {
        return persons.stream().map(PersonResponse::new).collect(Collectors.toList());
    }
}
