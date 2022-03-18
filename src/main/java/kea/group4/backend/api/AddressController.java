package kea.group4.backend.api;

import kea.group4.backend.dto.AddressRequest;
import kea.group4.backend.dto.AddressResponse;
import kea.group4.backend.services.AddressService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public AddressResponse addAddress(@RequestBody AddressRequest body) {
        return addressService.addAddress(body);
    }

    @PutMapping("/{id}")
    public AddressResponse editAddress(@RequestBody AddressRequest body, @PathVariable int id) {
        return addressService.editAddress(body, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable int id) {
        addressService.deleteAddress(id);
    }
}
