package kea.group4.backend.services;

import kea.group4.backend.dto.PersonAddressResponse;
import kea.group4.backend.dto.PersonRequest;
import kea.group4.backend.dto.PersonResponse;
import kea.group4.backend.entities.Address;
import kea.group4.backend.entities.Person;
import kea.group4.backend.entities.Role;
import kea.group4.backend.error.Client4xxException;
import kea.group4.backend.repositories.PersonRepository;
import kea.group4.backend.security.dto.SignupResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    PersonRepository personRepository;

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
        Optional<Person> personFromDatabase = personRepository.findByUsername(username);
        Address address = personFromDatabase.get().getAddress();
        // if address not null
        return new PersonAddressResponse(personFromDatabase.get(), address);
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
}