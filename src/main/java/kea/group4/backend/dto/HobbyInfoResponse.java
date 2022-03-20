package kea.group4.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import kea.group4.backend.entities.Hobby;
import kea.group4.backend.entities.HobbyInfo;
import kea.group4.backend.entities.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HobbyInfoResponse {
    Long id;
    Person person;
    Hobby hobby;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime dateSelected;

    public HobbyInfoResponse(HobbyInfo hobbyInfo) {
        this.id = hobbyInfo.getId();
        this.person = hobbyInfo.getPerson();
        this.hobby = hobbyInfo.getHobby();
        this.dateSelected = hobbyInfo.getDateSelected();
    }

    public static List<HobbyInfoResponse> getHobbyInfosFromEntities(List<HobbyInfo> hobbyInfos){
        return hobbyInfos.stream().map(HobbyInfoResponse::new).collect(Collectors.toList());

    }
}
