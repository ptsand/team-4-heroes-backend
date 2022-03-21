package kea.group4.backend.repositories;

import kea.group4.backend.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {

    Optional<Address> findByStreetAndHouseNumberAndFloorNumberAndDoorNumberAndZipCode(
            String street, int houseNumber, int floorNumber, String doorNumber, int zipCode);
}
