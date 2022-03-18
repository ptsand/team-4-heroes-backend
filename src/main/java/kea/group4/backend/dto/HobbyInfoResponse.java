package kea.group4.backend.dto;

import kea.group4.backend.entities.Hobby;
import kea.group4.backend.entities.HobbyInfo;
import kea.group4.backend.entities.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class HobbyInfoResponse {
    long id;
    Person person;
    Hobby hobby;

    public HobbyInfoResponse(HobbyInfo hobbyInfo) {
        this.id = hobbyInfo.getId();
        this.person = hobbyInfo.getPerson();
        this.hobby = hobbyInfo.getHobby();
    }

    public static List<HobbyInfoResponse> getHobbyInfosFromEntities(List<HobbyInfo> hobbyInfos){
        return hobbyInfos.stream().map(HobbyInfoResponse::new).collect(Collectors.toList());

    }
}
