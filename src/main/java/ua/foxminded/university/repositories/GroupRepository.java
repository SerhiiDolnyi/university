package ua.foxminded.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.university.domain.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}


