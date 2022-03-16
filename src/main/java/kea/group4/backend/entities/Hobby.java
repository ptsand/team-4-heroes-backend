package kea.group4.backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    public Hobby(long id ,String name, Boolean isInDoor ,String description, String category){
        this.id = id;
        this.name = name;
        this.isInDoor = isInDoor;
        this.description = description;
        this.category = category;
    }

}
