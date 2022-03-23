package kea.group4.backend.services;

import kea.group4.backend.dto.AddressRequest;
import kea.group4.backend.dto.AddressResponse;
import kea.group4.backend.entities.Address;
import kea.group4.backend.repositories.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    AddressRepository repo;

    AddressService service;

    Address address;

    @BeforeEach
    void setUp() {
        service = new AddressService(repo);
        address = new Address("street", 1, 1, "th", 2300);
    }

    @Test
    void addAddress_AddressNotInDatabase() {

        Mockito.when(repo.save(any(Address.class))).thenReturn(address);
        Mockito.when(repo.findByStreetAndHouseNumberAndFloorNumberAndDoorNumberAndZipCode(anyString(), anyInt(), anyInt(), anyString(), anyInt())).thenReturn(Optional.empty());

        AddressResponse response = service.addAddress(new AddressRequest(address.getStreet(), address.getHouseNumber(),address.getFloorNumber(), address.getDoorNumber(), address.getZipCode()));

        assertNotNull(response);
        assertAddressResponse(address, response);
    }

    @Test
    void addAddress_AddressExistsInDatabase() {
        Mockito.when(repo.findByStreetAndHouseNumberAndFloorNumberAndDoorNumberAndZipCode(anyString(), anyInt(), anyInt(), anyString(), anyInt())).thenReturn(Optional.of(address));

        AddressResponse response = service.addAddress(new AddressRequest(address.getStreet(), address.getHouseNumber(),address.getFloorNumber(), address.getDoorNumber(), address.getZipCode()));

        assertNotNull(response);
        assertAddressResponse(address, response);

        Mockito.verify(repo, never()).save(any(Address.class));
    }

    void assertAddressResponse(Address expected, AddressResponse actual) {
        assertEquals(expected.getStreet(), actual.getStreet());
        assertEquals(expected.getHouseNumber(), actual.getHouseNumber());
        assertEquals(expected.getFloorNumber(), actual.getFloorNumber());
        assertEquals(expected.getDoorNumber(), actual.getDoorNumber());
        assertEquals(expected.getZipCode(), actual.getZipCode());
    }
}