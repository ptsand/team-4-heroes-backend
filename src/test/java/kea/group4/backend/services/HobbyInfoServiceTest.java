package kea.group4.backend.services;

import kea.group4.backend.dto.HobbyInfoRequest;
import kea.group4.backend.dto.HobbyInfoResponse;
import kea.group4.backend.entities.Hobby;
import kea.group4.backend.entities.HobbyInfo;
import kea.group4.backend.entities.Person;
import kea.group4.backend.repositories.HobbyInfoRepository;
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
class HobbyInfoServiceTest {

    @Mock
    HobbyInfoRepository hobbyInfoRepository;
    HobbyInfoService hobbyInfoService;

    Person person = new Person("test@mail.dev","Firstname","Lastname",88888888);
    Hobby hobby1 = new Hobby("tempHobbyName",true,"https://temp.test","tempCat");
    Hobby hobby2 = new Hobby("tempHobby2Name",true,"https://temp2.test","tempCat");

    @BeforeEach
    void setup(){
        hobbyInfoService = new HobbyInfoService(hobbyInfoRepository);
    }

    @Test
    void testGetHobbyInfos() {
        Mockito.when(hobbyInfoRepository.findAll()).thenReturn(List.of(
                new HobbyInfo(person, hobby1),
                new HobbyInfo(person, hobby2)
        ));
        List<HobbyInfoResponse> hobbyInfos = hobbyInfoService.getHobbyInfos();
        assertEquals(2, hobbyInfos.size());
    }

    @Test
    void testGetHobbyInfo() {
        HobbyInfo hobbyInfo = new HobbyInfo(person, hobby1);
        hobbyInfo.setId(100L);
        Mockito.when(hobbyInfoRepository.findById(100L)).thenReturn(Optional.of(hobbyInfo));
        HobbyInfoResponse res = hobbyInfoService.getHobbyInfo(100L);
        assertEquals(100L,res.getId());
    }

    @Test
    void testAddHobbyInfo() {
        HobbyInfo hobbyInfo = new HobbyInfo(person, hobby1);
        hobbyInfo.setId(9L);
        Mockito.when(hobbyInfoRepository.save(any(HobbyInfo.class))).thenReturn(hobbyInfo);
        HobbyInfoResponse res = hobbyInfoService.addHobbyInfo(new HobbyInfoRequest(hobbyInfo.getPerson(),hobbyInfo.getHobby()));
        assertEquals(9L, res.getId());
    }

}