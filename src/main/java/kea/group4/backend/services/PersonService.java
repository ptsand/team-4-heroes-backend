package kea.group4.backend.services;

import kea.group4.backend.dto.*;
import kea.group4.backend.entities.Address;
import kea.group4.backend.entities.Person;
import kea.group4.backend.entities.Role;
import kea.group4.backend.error.Client4xxException;
import kea.group4.backend.repositories.AddressRepository;
import kea.group4.backend.repositories.PersonRepository;
import kea.group4.backend.security.dto.SignupResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    PersonRepository personRepository;

    AddressService addressService;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonResponse addPerson(PersonRequest body) {
        if(personRepository.existsByUsername(body.getUsername())){
            throw new Client4xxException("Username is taken");
        }
        if(personRepository.existsByEmail(body.getEmail())){
            throw new Client4xxException("Email is used by another user");
        }

        Person person = new Person(body);

        // All new users are by default given the role USER
        person.addRole(Role.USER);

        personRepository.save(person);
        return new PersonResponse(person);
    }

    public List<PersonResponse> getPersons() {
        List<Person> persons = personRepository.findAll();
        return PersonResponse.getPersonsFromEntities(persons);
    }

    public PersonResponse getPerson(long id) {
        Person person = personRepository.findById(id).orElseThrow(()->new Client4xxException("person not found"));
        return new PersonResponse(person);
    }

    public PersonAddressResponse getFullUserDetails(String username) {
        Person personFromDatabase = personRepository.findByUsername(username).orElseThrow(()->new Client4xxException("user data not found"));
        Address address = personFromDatabase.getAddress();

        if(address==null) {
            return new PersonAddressResponse(new PersonResponse(personFromDatabase));
        }
        return new PersonAddressResponse(new PersonResponse(personFromDatabase), new AddressResponse(address));
    }

    public PersonResponse editPerson(PersonRequest body, long id) {
        Person person = personRepository.findById(id).orElseThrow();
        person.setFirstName(body.getFirstName());
        person.setLastName(body.getLastName());
        person.setPhoneNumber(body.getPhoneNumber());
        return new PersonResponse(personRepository.save(person));
    }

    public void deletePerson(long id) {
        Person person = personRepository.findById(id).orElseThrow();
        personRepository.delete(person);
    }

    public void addAddressToPerson(AddressRequest body, long personId) {
        AddressResponse aResponse = addressService.addAddress(body);
        Address address = new Address(aResponse);
        Person person = personRepository.findById(personId).orElseThrow();
        person.setAddress(address);
        personRepository.save(person);
    }
}