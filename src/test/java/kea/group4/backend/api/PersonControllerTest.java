package kea.group4.backend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kea.group4.backend.dto.HobbyInfoRequest;
import kea.group4.backend.dto.PersonRequest;
import kea.group4.backend.entities.Hobby;
import kea.group4.backend.entities.Person;
import kea.group4.backend.error.Client4xxException;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setupPersonControllerTest() {
        // hobbyInfoRepository.deleteAll();
    }

    @Test
    void testAddPerson() throws Exception {
        PersonRequest personRequest = new PersonRequest("first","last",88888888,null,"testUser","test@mail.dk","s3cr3etpass");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/persons")
                        .contentType("application/json")
                        .accept("application/json")
                        .content(objectMapper.writeValueAsString(personRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        // Verify that it actually ended in the database
        assertEquals(1, personRepository.count());
    }
}