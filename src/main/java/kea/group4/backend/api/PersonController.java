package kea.group4.backend.api;

import kea.group4.backend.dto.*;
import kea.group4.backend.services.AddressService;
import kea.group4.backend.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/persons")
public class PersonController {

    PersonService personService;

    AddressService addressService;

    public PersonController(PersonService personService, AddressService addressService) {
        this.personService = personService;
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<PersonResponse> addPerson(@RequestBody @Valid PersonRequest body) {
        return ResponseEntity.ok(personService.addPerson(body));
    }

    @GetMapping
    public List<PersonResponse> getPersons(){
        return personService.getPersons();
    }

    @GetMapping("/{id}")
    public PersonResponse getPerson(@PathVariable long id) throws Exception {
        return personService.getPerson(id);
    }

    @GetMapping("/details")
    public PersonAddressResponse getPersonByUsername(@RequestParam String username) {
        System.out.println("getFullUserDetails()");
        return personService.getFullUserDetails(username);
    }

    @GetMapping("/{id}/hobbies")
    public PersonResponse getPersonHobbies(@PathVariable long id) throws Exception {
        return personService.getPerson(id);
    }

    @PutMapping("/{id}")
    public PersonResponse editPerson(@RequestBody PersonRequest body, @PathVariable long id){
        return personService.editPerson(body, id);
    }

    @PutMapping("/{username}/address")
    public AddressResponse addAddressToPerson(@RequestBody AddressRequest body, @PathVariable String username){
        return personService.addAddressToPerson(body, username);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable long id){
        personService.deletePerson(id);
    }
}
