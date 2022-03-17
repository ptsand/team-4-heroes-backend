package kea.group4.backend.services;
import kea.group4.backend.dto.HobbyRequest;
import kea.group4.backend.dto.HobbyResponse;
import kea.group4.backend.entities.Hobby;
import kea.group4.backend.repositories.HobbyRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class HobbyService extends RuntimeException{

    HobbyRepository hobbyRepository;

    public HobbyService (HobbyRepository hobbyRepository){
    this.hobbyRepository = hobbyRepository;
    }

    public List<HobbyResponse> getHobbeis(){
        List<Hobby> hobbies = hobbyRepository.findAll();
        return HobbyResponse.getHobbiesFromEntities(hobbies);
    }

    public HobbyResponse getHobby(long id){
        Hobby hobby = hobbyRepository.findById(id).orElseThrow();
        return new HobbyResponse(hobby);
    }
    public HobbyResponse addHobby(HobbyRequest body){
        Hobby newHobby = hobbyRepository.save(new Hobby(body));
        return new HobbyResponse(newHobby);
    }
    public HobbyResponse editHobby(HobbyRequest hobbyToEdit, long hobbyId) {
        Hobby hobby = hobbyRepository.findById(hobbyId).orElseThrow();
        hobby.setName(hobbyToEdit.getName());
        hobby.setIsInDoor(hobbyToEdit.getIsInDoor());
        hobby.setDescription(hobbyToEdit.getDescription());
        hobby.setCategory(hobbyToEdit.getCategory());
        return new HobbyResponse(hobbyRepository.save(hobby));
    }

    public void deleteHobby(long hobbyId){
        hobbyRepository.deleteById(hobbyId);
    }
}
