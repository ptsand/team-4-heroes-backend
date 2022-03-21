package kea.group4.backend.entities;

import kea.group4.backend.dto.AddressRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private int houseNumber;
    private int floorNumber;
    private String doorNumber;
    private int zipCode;

    public Address(String street, int houseNumber, int floorNumber, String doorNumber, int zipCode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.floorNumber = floorNumber;
        this.doorNumber = doorNumber;
        this.zipCode = zipCode;
    }

//TODO: Overload constructor?
public Address(long id, String street, int houseNumber, int floorNumber, String doorNumber, int zipCode) {
    this.id = id;
    this.street = street;
    this.houseNumber = houseNumber;
    this.floorNumber = floorNumber;
    this.doorNumber = doorNumber;
    this.zipCode = zipCode;
    }

    public Address(AddressRequest body) {
        this.street = body.getStreet();
        this.houseNumber = body.getHouseNumber();
        this.floorNumber = body.getFloorNumber();
        this.doorNumber = body.getDoorNumber();
        this.zipCode = body.getZipCode();
    }
}
