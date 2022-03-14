package kea.group4.backend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Hobby {
    @Id
    private String name;

    private String description;

    public Hobby(String name, String description){

        this.name = name;
        this.description = description;
    }

    public Hobby(){}

}
