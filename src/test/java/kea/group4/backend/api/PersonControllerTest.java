package kea.group4.backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kea.group4.backend.entities.Person;
import kea.group4.backend.repositories.HobbyInfoRepository;
import kea.group4.backend.repositories.PersonRepository;
import kea.group4.backend.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    HobbyInfoRepository hobbyInfoRepository;

    @Autowired
    PersonService personService;

    @BeforeEach
    public void setupPersonControllerTest() {
        hobbyInfoRepository.deleteAll();
        personRepository.deleteAll();
        personRepository.save(new Person("test1@email.dk", "Anders", "Andersen", 88888888));
        personRepository.save(new Person("test2@email.dk", "Bent", "Bendsen", 88888888));
        personRepository.save(new Person("test3@email.dk", "Charlotte", "Caroline", 88888888));
    }

}