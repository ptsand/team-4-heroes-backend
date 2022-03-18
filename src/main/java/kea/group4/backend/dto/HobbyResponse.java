package kea.group4.backend.dto;

import kea.group4.backend.entities.Hobby;
import kea.group4.backend.entities.HobbyInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class HobbyResponse {
    long id;
    String name;
    Boolean isInDoor;
    String description;
    String category;
    Set<HobbyInfo> hobbyInfos;

    public HobbyResponse(Hobby hobby){
        this.id = hobby.getId();
        this.name = hobby.getName();
        this.isInDoor = hobby.getIsInDoor();
        this.category = hobby.getCategory();
        this.description = hobby.getDescription();
        this.hobbyInfos = hobby.getHobbyInfos();
    }

    public static List<HobbyResponse> getHobbiesFromEntities(List<Hobby> hobbeis){
        return hobbeis.stream().map(HobbyResponse::new).collect(Collectors.toList());

    }
}
