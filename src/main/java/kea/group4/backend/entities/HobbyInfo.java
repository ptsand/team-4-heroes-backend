package kea.group4.backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class HobbyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @CreationTimestamp
    private Date dateSelected;
}
