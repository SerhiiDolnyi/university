package ua.foxminded.university.service.teacher;

import java.util.List;
import ua.foxminded.university.domain.Teacher;

public interface TeacherService {

    public List<Teacher> findAll();

    public Teacher findById(int teacherId);

    public void save (Teacher teacher);

    public void update (int teacherId, Teacher updatedTeacher);

    public void delete (int teacherId);    
}
