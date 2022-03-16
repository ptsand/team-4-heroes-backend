package kea.group4.backend.entities;

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
    int id;
    private String street;
    private String additionalInfo;
    private int zipCode;
    private String city;

    public Address(int id, String street, String additionalInfo, int zipCode, String city) {
        this.id = id;
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.zipCode = zipCode;
        this.city = city;
    }
}
