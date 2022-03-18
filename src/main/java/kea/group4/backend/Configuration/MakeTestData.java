package kea.group4.backend.Configuration;

import kea.group4.backend.entities.Address;
import kea.group4.backend.entities.Hobby;
import kea.group4.backend.entities.Person;
import kea.group4.backend.repositories.AddressRepository;
import kea.group4.backend.repositories.HobbyRepository;
import kea.group4.backend.repositories.PersonRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Profile("test")
public class MakeTestData implements ApplicationRunner {

    AddressRepository addressRepository;
    PersonRepository personRepository;
    HobbyRepository hobbyRepository;
    //HobbyInfoRepository hobbyInfoRepository;

    public MakeTestData(AddressRepository addressRepository, PersonRepository personRepository, HobbyRepository hobbyRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
        this.hobbyRepository = hobbyRepository;
    }
    public void makeAddresses() {
        Address address1 = new Address("street1", "additional info test address 1", 1111, "city1");
        Address address2 = new Address("street2", "additional info test address 2", 2222, "city2");
        Address address3 = new Address("street3", "additional info test address 3", 3333, "city3");

        addressRepository.save(address1);
        addressRepository.save(address2);
        addressRepository.save(address3);

        System.out.println("CREATED " + addressRepository.count() + " TEST ADDRESSES");
    }

    public void makeUsers() {
        Person user1 = new Person("user1@mail.dk", "AAA", "aaa", 11111111);
        Person user2= new Person("user2@mail.dk", "BBB", "bbb", 22222222);
        Person user3 = new Person("user3@mail.dk", "CCC", "ccc", 33333333);

        personRepository.save(user1);
        personRepository.save(user2);
        personRepository.save(user3);

        System.out.println("CREATED " + personRepository.count() + " TEST PERSONS");

    }

    public void makeHobbies() {
        Hobby hobby1 = new Hobby("hobby1", true, "www.hobby1.com", "category=test");
        Hobby hobby2 = new Hobby("hobby2", true, "www.hobby2.com", "category=test");
        Hobby hobby3 = new Hobby("hobby3", true, "www.hobby3.com", "category=test");

        hobbyRepository.save(hobby1);
        hobbyRepository.save(hobby2);
        hobbyRepository.save(hobby3);

        System.out.println("CREATED " + hobbyRepository.count() + " TEST HOBBIES");

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addressRepository.deleteAll();
        personRepository.deleteAll();
        hobbyRepository.deleteAll();

        makeAddresses();
        makeUsers();
        makeHobbies();
    }
}
