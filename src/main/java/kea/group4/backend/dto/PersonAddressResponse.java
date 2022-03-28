package kea.group4.backend.dto;

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
    private PersonResponse person;
    private AddressResponse address;

    public PersonAddressResponse(PersonResponse personResponse) {
        this.person = personResponse;
        this.address = null;
    }
}
