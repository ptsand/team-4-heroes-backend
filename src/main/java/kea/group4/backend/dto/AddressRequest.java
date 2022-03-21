package kea.group4.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    private String street;
    private int houseNumber;
    private int floorNumber;
    private String doorNumber;
    private int zipCode;
}
