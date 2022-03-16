package kea.group4.backend.services;

import kea.group4.backend.dto.AddressRequest;
import kea.group4.backend.dto.AddressResponse;
import kea.group4.backend.entities.Address;
import kea.group4.backend.error.Client4xxException;
import kea.group4.backend.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public AddressResponse getAddress(int id){
        return new AddressResponse(addressRepository.findById(id).orElseThrow(() -> new Client4xxException("address not found")));
    }

    public AddressResponse addAddress(AddressRequest body) {
        Address newAddress = addressRepository.save(new Address(body));
        return new AddressResponse(newAddress);
    }

    public AddressResponse editAddress(AddressRequest body, int id) {
        Address addressToEdit = new Address(body);
        addressToEdit.setId(id);
        addressRepository.save(addressToEdit);
        return new AddressResponse(addressToEdit);

    }

    public void deleteAddress(int id) {
        addressRepository.deleteById(id);
    }
}
