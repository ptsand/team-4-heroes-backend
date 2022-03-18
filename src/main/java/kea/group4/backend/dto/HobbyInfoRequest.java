package kea.group4.backend.dto;

import kea.group4.backend.entities.Hobby;
import kea.group4.backend.entities.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HobbyInfoRequest {
    private Person person;
    private Hobby hobby;
}
