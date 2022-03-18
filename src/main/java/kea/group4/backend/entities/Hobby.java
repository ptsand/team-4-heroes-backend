package kea.group4.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kea.group4.backend.dto.HobbyRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Getter @Setter
public class Hobby {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Boolean isInDoor;

    private String description;

    private  String category;
    @JsonIgnore
    @OneToMany(mappedBy = "hobby")
    private Set<HobbyInfo> hobbyInfos = new HashSet<HobbyInfo>();

    public Hobby(String name, Boolean isInDoor, String description, String category){
        this.name = name;
        this.isInDoor = isInDoor;
        this.description = description;
        this.category = category;
    }

    public Hobby(HobbyRequest body){
        this.name = body.getName();
        this.isInDoor = body.getIsInDoor();
        this.description = body.getDescription();
        this.category = body.getCategory();
    }





}
