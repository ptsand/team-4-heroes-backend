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

    private String environment;

    private String description;

    private  String category;
    @JsonIgnore
    @OneToMany(mappedBy = "hobby", fetch = FetchType.EAGER)
    private Set<HobbyInfo> hobbyInfos = new HashSet<HobbyInfo>();

    public Hobby(String name, String environment, String description, String category){
        this.name = name;
        this.environment = environment;
        this.description = description;
        this.category = category;
    }

    public Hobby(HobbyRequest body){
        this.name = body.getName();
        this.environment = body.getEnvironment();
        this.description = body.getDescription();
        this.category = body.getCategory();
    }

}
