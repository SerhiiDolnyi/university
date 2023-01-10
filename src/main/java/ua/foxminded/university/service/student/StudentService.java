package ua.foxminded.university.service.student;

import java.util.List;
import java.util.Optional;

import ua.foxminded.university.domain.Group;
import ua.foxminded.university.domain.Student;
import ua.foxminded.university.exception.UserInputException;

public interface StudentService {

    public List<Student> findAll();

    public Student findById(int studentId);

    public void save (Student student);

    public void update (int studentId, Student updatedStudent);

    public void delete (int studentId);
}
