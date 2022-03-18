package kea.group4.backend.dto;

import kea.group4.backend.entities.Address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class AddressResponse {
    private int id;
    private String street;
    private String additionalInfo;
    private int zipCode;
    private String city;

    public AddressResponse(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        this.zipCode = address.getZipCode();
        this.city = address.getCity();
    }

    public static List<AddressResponse> getAddressesFromEntities(List<Address> addresses) {
        return addresses.stream().map(AddressResponse::new).collect(Collectors.toList());
    }
}
