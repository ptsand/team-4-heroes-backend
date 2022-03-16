package kea.group4.backend.dto;

import kea.group4.backend.entities.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressResponse {
    private String street;
    private String additionalInfo;
    private int zipCode;
    private String city;

    public addressResponse(Address address) {
        this.id = address.getId();
    }
}
