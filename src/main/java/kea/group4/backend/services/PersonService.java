package kea.group4.backend.services;

import kea.group4.backend.dto.PersonRequest;
import kea.group4.backend.dto.PersonResponse;
import kea.group4.backend.entities.Person;
import kea.group4.backend.error.Client4xxException;
import kea.group4.backend.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonResponse addPerson(PersonRequest body) {
        Person person = personRepository.save(new Person(body));
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

    public PersonResponse getPersonByUsername(String username) {
        Optional<Person> personFromDatabase = personRepository.findByUsername(username);
        return new PersonResponse(personFromDatabase.get());
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