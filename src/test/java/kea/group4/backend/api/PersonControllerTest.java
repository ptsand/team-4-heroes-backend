package kea.group4.backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kea.group4.backend.dto.PersonRequest;
import kea.group4.backend.entities.Person;
import kea.group4.backend.repositories.HobbyInfoRepository;
import kea.group4.backend.repositories.PersonRepository;
import kea.group4.backend.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
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
        personRepository.deleteAll();
    }

    @Test
    void testAddPerson() throws Exception {
        PersonRequest personRequest = new PersonRequest("first", "last", 88888888, null, "testUser", "test@mail.dk", "s3cr3etpass");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/persons/register")
                        .contentType("application/json")
                        .accept("application/json")
                        .content(objectMapper.writeValueAsString(personRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        // Verify that it actually ended in the database
        assertEquals(1, personRepository.count());
    }

    @Test
    void testAddPersonWhenUsernameExists() throws Exception {
        personRepository.save(new Person("user@mail.dk", "AAA", "aaa", 11111111,"testUser","passw0rd"));
        PersonRequest personRequest = new PersonRequest("first", "last", 88888888, null, "testUser", "test@mail.dk", "passw0rd");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/persons/register")
                        .contentType("application/json")
                        .accept("application/json")
                        .content(objectMapper.writeValueAsString(personRequest)))
                .andExpect(status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value("Username is already taken"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("CONFLICT"));
        // Verify that only one person actually ended in the database
        assertEquals(1, personRepository.count());
    }

    @Test
    void testPersonNotFound() throws Exception {
        // request a nonexistent person and verify HTTP Status and error response
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/555")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value("Person not found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("NOT_FOUND"));
    }

}
