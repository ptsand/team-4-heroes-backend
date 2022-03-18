package kea.group4.backend.repositories;

import kea.group4.backend.entities.HobbyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyInfoRepository extends JpaRepository<HobbyInfo,Long> {
}
