package kea.group4.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {

    private String email;
    private String firstName;
    private String lastName;
    private long phoneNumber;

}
