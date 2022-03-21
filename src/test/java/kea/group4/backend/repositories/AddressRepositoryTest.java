package kea.group4.backend.repositories;

import kea.group4.backend.entities.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    AddressRepository repo;

    Address expected, addr;

    @BeforeEach
    void setUp() {
        expected = new Address("street_1", 1, 1, "1tv", 2300);
        repo.save(expected);
        addr = new Address("street_2", 2, 2, "1tv", 2300);
    }

    @Test
    void findByStreetAndHouseNumberAndFloorNumberAndDoorNumberAndZipCode() {

        Optional<Address> optionalActual = repo.findByStreetAndHouseNumberAndFloorNumberAndDoorNumberAndZipCode(
                expected.getStreet(), expected.getHouseNumber(), expected.getFloorNumber(), expected.getDoorNumber(), expected.getZipCode());

        assertTrue(optionalActual.isPresent());

        var actual = optionalActual.get();
        assertTrue(actual.getId() > 0);

        assertEquals(expected, actual);

        Optional<Address> unsaved = repo.findByStreetAndHouseNumberAndFloorNumberAndDoorNumberAndZipCode(
                addr.getStreet(), addr.getHouseNumber(), addr.getFloorNumber(), addr.getDoorNumber(), addr.getZipCode());
        assertTrue(unsaved.isEmpty());
    }
}