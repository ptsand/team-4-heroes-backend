package kea.group4.backend.repositories;

import kea.group4.backend.entities.Hobby;
import kea.group4.backend.entities.HobbyInfo;
import kea.group4.backend.entities.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HobbyInfoRepositoryTest {

    @Autowired
    HobbyInfoRepository hobbyInfoRepository;

    Person testPerson;
    Hobby testHobby1, testHobby2;
    List<HobbyInfo> hobbyInfos = new ArrayList<>();

    @BeforeEach
    void setup(@Autowired PersonRepository personRepository, @Autowired HobbyRepository hobbyRepository) {
        hobbyInfoRepository.deleteAll();
        testHobby1 = hobbyRepository.save(new Hobby("tempHobbyName",true,"https://temp.test","tempCat"));
        testHobby2 = hobbyRepository.save(new Hobby("tempHobbyName2",false,"https://temp2.test","tempCat"));
        testPerson = personRepository.save(new Person("test@mail.dev","Firstname","Lastname",88888888));
        hobbyInfos.add(hobbyInfoRepository.save(new HobbyInfo(testPerson, testHobby1)));
        hobbyInfos.add(hobbyInfoRepository.save(new HobbyInfo(testPerson, testHobby2)));
    }

    @Test
    public void testGetHobbyInfoById() {
        HobbyInfo hobbyInfo = hobbyInfoRepository.getById(hobbyInfos.get(0).getId());
        assertEquals(hobbyInfo.getId(), hobbyInfos.get(0).getId());
    }

    @Test
    public void testGetAllHobbyInfos() {
        List<HobbyInfo> hobbyInfosFromRepos = hobbyInfoRepository.findAll();
        assertEquals(hobbyInfosFromRepos.size(), hobbyInfos.size());
        assertThat(hobbyInfosFromRepos, containsInAnyOrder(hasProperty("id", is(hobbyInfos.get(0).getId())),
                hasProperty("id", is(hobbyInfos.get(1).getId()))));
    }

    @Test
    public void testCreateHobbyInfo() {

    }

    @Test
    public void testDeleteHobbyInfo() {

    }

}