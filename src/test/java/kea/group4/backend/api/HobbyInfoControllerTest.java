package kea.group4.backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kea.group4.backend.dto.HobbyInfoRequest;
import kea.group4.backend.entities.Hobby;
import kea.group4.backend.entities.HobbyInfo;
import kea.group4.backend.entities.Person;
import kea.group4.backend.error.Client4xxException;
import kea.group4.backend.repositories.HobbyInfoRepository;
import kea.group4.backend.repositories.HobbyRepository;
import kea.group4.backend.repositories.PersonRepository;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class HobbyInfoControllerTest {
    
    @Autowired
    HobbyInfoRepository hobbyInfoRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    HobbyRepository hobbyRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    private Hobby testHobby1, testHobby2;
    private Person testPerson;
    private List<HobbyInfo> hobbyInfos = new ArrayList<>();

    @BeforeEach
    public void setup() {
        hobbyInfoRepository.deleteAll();
        personRepository.deleteAll();
        testHobby1 = hobbyRepository.save(new Hobby("tempHobbyName",true,"https://temp.test","tempCat"));
        testHobby2 = hobbyRepository.save(new Hobby("tempHobbyName2",false,"https://temp2.test","tempCat"));
        testPerson = personRepository.save(new Person("test@mail.dev","Firstname1","Lastname1",88888888));
        hobbyInfos.add(hobbyInfoRepository.save(new HobbyInfo(testPerson, testHobby1)));
        hobbyInfos.add(hobbyInfoRepository.save(new HobbyInfo(testPerson, testHobby2)));
    }

    @Test
    public void testGetHobbyInfoById() throws Exception {
        HobbyInfo hi = hobbyInfos.get(0);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/hobby-infos/" + hi.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(hi.getId()));
        Person p = personRepository.findById(testPerson.getId()).orElseThrow(()->new Client4xxException("Person not found"));
        assertEquals(p.getHobbyInfos().size(),hobbyInfos.size());
    }

    @Test
    public void testGetAllHobbyInfos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/hobby-infos")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(hobbyInfos.size()));
    }

    @Test
    public void testCreateHobbyInfo() throws Exception {
        Hobby newHobby = hobbyRepository.save(new Hobby("tempHobbyName3",false,"https://temp3.test","tempCat"));
        HobbyInfoRequest hiReq = new HobbyInfoRequest(testPerson, newHobby);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/hobby-infos")
                        .contentType("application/json")
                        .accept("application/json")
                        .content(objectMapper.writeValueAsString(hiReq)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        // Verify that it actually ended in the database
        assertEquals(hobbyInfos.size()+1, hobbyInfoRepository.count());
        // Verify the HobbyInfo got added to the testPersons hobbyInfos Set
        Person p = personRepository.findById(testPerson.getId()).orElseThrow(()->new Client4xxException("Person not found"));
        assertEquals(p.getHobbyInfos().size(), 3); // testPerson should now have all 3 HobbyInfos
    }

    @Test
    public void testDeleteHobbyInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/hobby-infos/"+ hobbyInfos.get(0).getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        assertEquals(hobbyInfos.size()-1, hobbyInfoRepository.count());
    }
}