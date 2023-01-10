package ua.foxminded.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.university.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
}
