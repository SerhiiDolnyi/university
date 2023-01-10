package ua.foxminded.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.foxminded.university.domain.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
