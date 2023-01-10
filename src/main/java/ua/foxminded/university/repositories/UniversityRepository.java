package ua.foxminded.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.foxminded.university.domain.university.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}
