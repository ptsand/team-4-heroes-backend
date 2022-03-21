package kea.group4.backend.services;

import kea.group4.backend.dto.AddressRequest;
import kea.group4.backend.dto.AddressResponse;
import kea.group4.backend.entities.Address;
import kea.group4.backend.error.Client4xxException;
import kea.group4.backend.repositories.AddressRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class AddressService {
    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressResponse> getAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return AddressResponse.getAddressesFromEntities(addresses);
    }

    public AddressResponse getAddress(long id){
        Address addressToFind = addressRepository.findById(id).orElseThrow(() -> new Client4xxException("address not found"));
        return new AddressResponse(addressToFind);
    }

// https://www.baeldung.com/spring-data-exists-query#:~:text=4.%20Using%20a,well%2Dtested%20framework.
    public AddressResponse addAddress(AddressRequest body) {

        Address newAddress = new Address(body);
        Address addressFromDatabase = addressRepository.findByStreetAndHouseNumberAndFloorNumberAndDoorNumberAndZipCode(
                newAddress.getStreet(),
                newAddress.getHouseNumber(),
                newAddress.getFloorNumber(),
                newAddress.getDoorNumber(),
                newAddress.getZipCode()
        );

        if(addressFromDatabase != null) {
            return new AddressResponse(addressFromDatabase);
        }

        addressRepository.save(newAddress);
        return new AddressResponse(newAddress);
    }

    public AddressResponse editAddress(AddressRequest body, long id) {
        Address addressToEdit = new Address(body);
        addressToEdit.setId(id);
        addressRepository.save(addressToEdit);
        return new AddressResponse(addressToEdit);
    }

    public void deleteAddress(long id) {
        addressRepository.deleteById(id);
    }
}
