package kea.group4.backend.repositories;

import kea.group4.backend.entities.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby,Long> {
}
