package kea.group4.backend.services;

import kea.group4.backend.dto.HobbyRequest;
import kea.group4.backend.dto.HobbyResponse;
import kea.group4.backend.entities.Hobby;
import kea.group4.backend.repositories.HobbyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class HobbyServiceTest {

    @Mock
    HobbyRepository hobbyRepository;

    HobbyService hobbyService;

    @BeforeEach
    void setUp(){
        hobbyService = new HobbyService(hobbyRepository);
    }

    @Test
    void testGetHobbeis() {
        Mockito.when(hobbyRepository.findAll()).thenReturn(List.of(
                new Hobby("test1" ,true, "gitte", "kampsport"),
                new Hobby("test2" ,true, "gitte", "gittesport")
        ));
        List<HobbyResponse> hobbeis = hobbyService.getHobbeis();
        assertEquals(2,hobbeis.size());
    }

    @Test
    void getHobby() {
        Hobby testHobby = new Hobby("Test1", true, "gitte", "aslkd");
        long id = 100;
        testHobby.setId(id);
        Mockito.when(hobbyRepository.findById(id)).thenReturn(Optional.of(testHobby));
        HobbyResponse hobbyRes = hobbyService.getHobby(100);
        assertEquals("aslkd", hobbyRes.getCategory());
    }

    @Test
    void addHobby() {
        Hobby testHobby = new Hobby("gitte", true, "test1", "test2");
        testHobby.setId(100);
        Mockito.when(hobbyRepository.save(any(Hobby.class))).thenReturn(testHobby);
        HobbyResponse hobbyRes = hobbyService.addHobby(new HobbyRequest(testHobby.getName(), testHobby.getIsInDoor(),testHobby.getDescription(), testHobby.getCategory()));
        assertEquals(100, hobbyRes.getId());
    }

    /*@Test
    void editHobby() {
    }

    @Test
    void deleteHobby() {
    }*/
}