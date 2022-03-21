package kea.group4.backend.services;

import kea.group4.backend.dto.AddressRequest;
import kea.group4.backend.dto.AddressResponse;
import kea.group4.backend.entities.Address;
import kea.group4.backend.error.Client4xxException;
import kea.group4.backend.repositories.AddressRepository;
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
        return new AddressResponse(findAddress(id));
    }

    public AddressResponse addAddress(AddressRequest body) {
        Address newAddress = addressRepository.save(new Address(body));

        ExampleMatcher addressMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("street", ignoreCase());

        return new AddressResponse(newAddress);
        //TODO: If address already exits in database, return corresponding response.
        // Else add address to database and return response
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

    private Address findAddress(long id) {
        return addressRepository.findById(id).orElseThrow(() -> new Client4xxException("address not found"));
    }


}
