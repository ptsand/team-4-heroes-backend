package kea.group4.backend.dto;

import kea.group4.backend.entities.Hobby;

public class HobbyResponse {
    long id;
    String name;
    String description;

    public HobbyResponse(Hobby hobby){
        this.id = hobby.getId();
        this.name = hobby.getName();
        this.description = hobby.getDescription();
    }
}
