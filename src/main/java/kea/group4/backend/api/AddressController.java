package kea.group4.backend.api;

import kea.group4.backend.dto.AddressResponse;
import kea.group4.backend.services.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/addresses")
public class AddressController {
    AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressResponse> getAddresses() {
        return addressService.getAddresses();
    }

    @GetMapping("/{id}")
    public AddressResponse getAddress(@PathVariable int id) {
        return addressService.getAddress(id);
    }

}
