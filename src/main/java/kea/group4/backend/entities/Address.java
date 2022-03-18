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
    long id;
    private String street;
    private String additionalInfo;
    private int zipCode;
    private String city;

    public Address(String street, String additionalInfo, int zipCode, String city) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.zipCode = zipCode;
        this.city = city;
    }

    public Address(AddressRequest body) {
        this.street = body.getStreet();
        this.additionalInfo = body.getAdditionalInfo();
        this.zipCode = body.getZipCode();
        this.city = body.getCity();
    }
}
