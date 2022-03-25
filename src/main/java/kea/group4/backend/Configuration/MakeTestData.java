package kea.group4.backend.Configuration;

import kea.group4.backend.entities.*;
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
        hobbyRepository.save(new Hobby("Breakdancing","Udendørs","https://en.wikipedia.org/wiki/Breakdancing","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Climbing","Udendørs","https://en.wikipedia.org/wiki/Climbing","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Cricket","Udendørs","https://en.wikipedia.org/wiki/Cricket","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Croquet","Udendørs","https://en.wikipedia.org/wiki/Croquet","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Cycling","Udendørs","https://en.wikipedia.org/wiki/Cycling","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Disc golf","Udendørs","https://en.wikipedia.org/wiki/Disc_golf","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Dog sport","Udendørs","https://en.wikipedia.org/wiki/Dog_sport","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Equestrianism","Udendørs","https://en.wikipedia.org/wiki/Equestrianism","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Exhibition drill","Udendørs","https://en.wikipedia.org/wiki/Exhibition_drill","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Field hockey","Udendørs","https://en.wikipedia.org/wiki/Field_hockey","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Figure skating","Udendørs","https://en.wikipedia.org/wiki/Figure_skating","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Fishing","Udendørs","https://en.wikipedia.org/wiki/Fishing","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Footbag","Udendørs","https://en.wikipedia.org/wiki/Footbag","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Frisbee","Udendørs","https://en.wikipedia.org/wiki/Frisbee","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Golf","Udendørs","https://en.wikipedia.org/wiki/Golf","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Handball","Udendørs","https://en.wikipedia.org/wiki/Handball","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Horseback riding","Udendørs","https://en.wikipedia.org/wiki/Horseback_riding","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Horseshoes","Udendørs","https://en.wikipedia.org/wiki/Horseshoes","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Iceboat","Udendørs","https://en.wikipedia.org/wiki/Iceboat","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Jukskei","Udendørs","https://en.wikipedia.org/wiki/Jukskei","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Kart racing","Udendørs","https://en.wikipedia.org/wiki/Kart_racing","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Knife throwing","Udendørs","https://en.wikipedia.org/wiki/Knife_throwing","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Lacrosse","Udendørs","https://en.wikipedia.org/wiki/Lacrosse","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Longboarding","Udendørs","https://en.wikipedia.org/wiki/Longboarding","Konkurrence hobbyer"));
        hobbyRepository.save(new Hobby("Long-distance running","Udendørs","https://en.wikipedia.org/wiki/Long-distance_running","Konkurrence hobbyer"));
        System.out.println("CREATED " + hobbyRepository.count() + " TEST HOBBIES");

    }

    public void makeUsers() {
        Person user1 = new Person("user1@mail.dk", "AAA", "aaa", 11111111,"user1","passw0rd1");
        Person user2= new Person("user2@mail.dk", "BBB", "bbb", 22222222,"user2","passw0rd2");
        user1.addRole(Role.USER);
        user2.addRole(Role.ADMIN);
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
        String email = user.getHobbyInfos().stream().collect(Collectors.toList()).get(0).getPerson().getEmail(); // TODO: remove this?
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        makeAddresses();
        makeHobbies();
        makeUsers();
        makeHobbyInfos();
    }
}