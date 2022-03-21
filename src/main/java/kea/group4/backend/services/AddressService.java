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
        return new AddressResponse(findAddress(id));
    }

    // https://www.baeldung.com/spring-data-exists-query#:~:text=5.1.%20The%20Matcher,fits%20the%20need.
    public AddressResponse addAddress(AddressRequest body) {

        //TODO: If address already exits in database, return corresponding response.
        ExampleMatcher addressMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("street", ignoreCase())
                .withMatcher("houseNumber", ignoreCase())
                .withMatcher("floorNumber", ignoreCase())
                .withMatcher("doorNumber", ignoreCase())
                .withMatcher("zipCode", ignoreCase());

        Address probe = new Address(body);
        Example<Address> example = Example.of(probe, addressMatcher);
        var exists = addressRepository.exists(example);

        if(exists) {
            return null;
        }

        // TODO: Else add address to database and return response
        Address newAddress = addressRepository.save(new Address(body));
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

    private Address findAddress(long id) {
        return addressRepository.findById(id).orElseThrow(() -> new Client4xxException("address not found"));
    }


}
