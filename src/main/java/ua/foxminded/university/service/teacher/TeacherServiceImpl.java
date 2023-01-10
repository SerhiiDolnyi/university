package ua.foxminded.university.service.teacher;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.foxminded.university.domain.Teacher;
import ua.foxminded.university.repositories.TeacherRepository;

@Service
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {
    TeacherRepository teacherRepository;
    
    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }    

    @Override
    public List<Teacher> findAll(){
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(int teacherId){
        Optional<Teacher> foundTeacher = teacherRepository.findById(teacherId);
        Teacher teacher = foundTeacher.orElse(null);
        return teacher;
    }

    @Override
    @Transactional
    public void save (Teacher teacher){
        teacherRepository.save(teacher);
    }

    @Override
    @Transactional
    public void update (int teacherId, Teacher updatedTeacher){
        updatedTeacher.setTeacherId(teacherId);
           teacherRepository.save(updatedTeacher);
    }

    @Override
    @Transactional
    public void delete (int teacherId){
        teacherRepository.deleteById(teacherId);
    }
}
