package kea.group4.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kea.group4.backend.dto.AddressRequest;
import kea.group4.backend.dto.AddressResponse;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonIgnore
    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER) // TODO: Which CascadeType should be used? Address should be removed ONLY IF it doesn't have any associated Persons
    private Set<Person> persons = new HashSet<>();

    public Address(String street, int houseNumber, int floorNumber, String doorNumber, int zipCode) {
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

    public Address(AddressResponse addressResponse) {
        this.id = addressResponse.getId();
        this.street = addressResponse.getStreet();
        this.houseNumber = addressResponse.getHouseNumber();
        this.floorNumber = addressResponse.getFloorNumber();
        this.doorNumber = addressResponse.getDoorNumber();
        this.zipCode = addressResponse.getZipCode();
    }
}
