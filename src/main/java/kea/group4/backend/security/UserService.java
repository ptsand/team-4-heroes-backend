package kea.group4.backend.security;

import kea.group4.backend.dto.PersonRequest;
import kea.group4.backend.entities.Person;
import kea.group4.backend.entities.Role;
import kea.group4.backend.error.Client4xxException;
import kea.group4.backend.repositories.PersonRepository;
import kea.group4.backend.security.dto.SignupResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    PersonRepository userRepository;

    public UserService(PersonRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SignupResponse createUser(PersonRequest request){

        if(userRepository.existsByUsername(request.getUsername())){
            throw new Client4xxException("Username is taken");
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new Client4xxException("Email is used by another user");
        }

        Person user = new Person(request);


        //All new users are by default given the role CUSTOMER. Comment out the lines below if this is not your required behaviour
        user.addRole(Role.USER);

        userRepository.save(user);
        List<String> roleNames = user.getRoles().stream().map(role -> role.toString()).collect(Collectors.toList());
        //No need to return a body since primary key is the provided userName
        return new SignupResponse(roleNames);

    }

}
