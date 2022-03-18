package kea.group4.backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kea.group4.backend.entities.Address;
import kea.group4.backend.repositories.AddressRepository;
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

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AddressControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired

    AddressRepository addressRepository;

    @Autowired
    ObjectMapper objectMapper;

    static long addressOneId, addressTwoId;

    @BeforeEach
    void setUp() {
        addressRepository.deleteAll();
        addressOneId = addressRepository.save(new Address("streetname1", "some info", 2300, "Kbh")).getId();
        addressTwoId = addressRepository.save(new Address("streetname2", "some info", 2300, "Kbh")).getId();
    }

    @Test
    void getAddressesById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/addresses/" + addressOneId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(addressOneId))
                .andExpect(jsonPath(("$.street")).value("streetname1"));
    }

    @Test
    void getAddresses() throws Exception {
        // https://support.smartbear.com/alertsite/docs/monitors/api/endpoint/jsonpath.html#:~:text=%5B%3F(expression)%5D,node%20being%20processed.
        String zip = "$[?(@.zipCode == %d)]";
        String street = "$[?(@.street == '%s')]";  // for each element in this array, zip equals placeholder string
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/addresses/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath(zip, 2300).exists())
                .andExpect(jsonPath(street, "streetname2").exists());
    }

    @Test
    void addAddress() throws Exception {
        Address address = new Address("street3", "blablainfo", 1411, "KBH K");
        mockMvc.perform(MockMvcRequestBuilders
                    .post("/api/addresses/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(address)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id", notNullValue()));

        assertEquals(3, addressRepository.count());




    }

    @Test
    void editAddress() {

    }

    @Test
    void deleteAddress() {
    }
}