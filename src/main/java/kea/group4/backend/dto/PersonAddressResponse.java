package kea.group4.backend.dto;

import kea.group4.backend.entities.Address;
import kea.group4.backend.entities.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.SecondaryTable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonAddressResponse {
    private Person person;
    private Address address;
}
