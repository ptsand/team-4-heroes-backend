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
    private long id;
    private String street;
    private int houseNumber;
    private int floorNumber;
    private String doorNumber;
    private int zipCode;

    public AddressResponse(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.houseNumber = address.getHouseNumber();
        this.floorNumber = address.getFloorNumber();
        this.doorNumber = address.getDoorNumber();
        this.zipCode = address.getZipCode();
    }

    public static List<AddressResponse> getAddressesFromEntities(List<Address> addresses) {
        return addresses.stream().map(AddressResponse::new).collect(Collectors.toList());
    }
}
