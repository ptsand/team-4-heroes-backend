package kea.group4.backend.Configuration;

import kea.group4.backend.entities.Address;
import kea.group4.backend.entities.Hobby;
import kea.group4.backend.entities.HobbyInfo;
import kea.group4.backend.entities.Person;
import kea.group4.backend.error.Client4xxException;
import kea.group4.backend.repositories.AddressRepository;
import kea.group4.backend.repositories.HobbyInfoRepository;
import kea.group4.backend.repositories.HobbyRepository;
import kea.group4.backend.repositories.PersonRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Profile("!test")
public class MakeTestData implements ApplicationRunner {

    AddressRepository addressRepository;
    PersonRepository personRepository;
    HobbyRepository hobbyRepository;
    HobbyInfoRepository hobbyInfoRepository;

    public MakeTestData(AddressRepository addressRepository, PersonRepository personRepository, HobbyRepository hobbyRepository, HobbyInfoRepository hobbyInfoRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
        this.hobbyRepository = hobbyRepository;
        this.hobbyInfoRepository = hobbyInfoRepository;
    }

    public void makeAddresses() {
        Address address1 = new Address("street1", 11, 4, "th", 1111);
        Address address2 = new Address("street2", 12, 2, "th", 2222);
        Address address3 = new Address("street3", 13, 1, "th", 3333);

        addressRepository.save(address1);
        addressRepository.save(address2);
        addressRepository.save(address3);

        System.out.println("CREATED " + addressRepository.count() + " TEST ADDRESSES");
    }

    public void makeHobbies() {
        Hobby hobby1 = new Hobby("hobby1", "indendørs", "www.hobby1.com", "category=test");
        Hobby hobby2 = new Hobby("hobby2", "indendørs", "www.hobby2.com", "category=test");

        hobbyRepository.save(hobby1);
        hobbyRepository.save(hobby2);

        System.out.println("CREATED " + hobbyRepository.count() + " TEST HOBBIES");

    }

    public void makeUsers() {
        Person user1 = new Person("user1@mail.dk", "AAA", "aaa", 11111111,"user1","passw0rd1");
        Person user2= new Person("user2@mail.dk", "BBB", "bbb", 22222222,"user2","passw0rd2");

        personRepository.save(user1);
        personRepository.save(user2);

        System.out.println("CREATED " + personRepository.count() + " TEST PERSONS");

    }

    public void makeHobbyInfos(){
        Person user3 = new Person("user3@mail.dk", "CCC", "ccc", 33333333);
        Hobby hobby3 = new Hobby("hobby3", "indendørs", "www.hobby3.com", "category=test");
        personRepository.save(user3);
        hobbyRepository.save(hobby3);
        HobbyInfo hobbyInfo = new HobbyInfo(user3,hobby3);
        hobbyInfoRepository.save(hobbyInfo);
        Person user = personRepository.findById(user3.getId()).orElseThrow(()->new Client4xxException("not found"));
        String email = user.getHobbyInfos().stream().collect(Collectors.toList()).get(0).getPerson().getEmail();
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        makeAddresses();
        makeHobbies();
        makeUsers();
        makeHobbyInfos();
    }
}
