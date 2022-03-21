package kea.group4.backend.entities;

import kea.group4.backend.dto.HobbyInfoRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class HobbyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Person person;
    @ManyToOne
    Hobby hobby;

    @CreationTimestamp
    LocalDateTime dateSelected;

    public HobbyInfo(Person person, Hobby hobby) {
        this.person = person;
        this.hobby = hobby;
    }

    public HobbyInfo(HobbyInfoRequest hobbyInfoRequest) {
        this.person = hobbyInfoRequest.getPerson();
        this.hobby = hobbyInfoRequest.getHobby();
    }
}
