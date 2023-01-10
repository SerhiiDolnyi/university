package ua.foxminded.university.service.student;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ua.foxminded.university.domain.Student;
import ua.foxminded.university.repositories.StudentRepository;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService{

    StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll () {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int studentId) {
        Optional<Student> foundStudent = studentRepository.findById(studentId);
        return foundStudent.orElse(null);
    }

    @Override
    @Transactional
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void update(int studentId, Student updatedStudent) {
        updatedStudent.setStudentId(studentId);
        studentRepository.save(updatedStudent);
    }

    @Override
    @Transactional
    public void delete(int studentId) {
        studentRepository.deleteById(studentId);
    }
}
