package kea.group4.backend.dto;

import kea.group4.backend.entities.Hobby;

public class HobbyResponse {
    long id;
    String name;
    Boolean isInDoor;
    String description;
    String category;

    public HobbyResponse(Hobby hobby){
        this.id = hobby.getId();
        this.name = hobby.getName();
        this.isInDoor = hobby.getIsInDoor();
        this.category = hobby.getCategory();
        this.description = hobby.getDescription();
    }
}
