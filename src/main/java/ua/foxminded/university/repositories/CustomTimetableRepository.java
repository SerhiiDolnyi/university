//package ua.foxminded.university.repositories;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import ua.foxminded.university.domain.Group;
//import ua.foxminded.university.domain.Teacher;
//import ua.foxminded.university.domain.timetable.Location;
//import ua.foxminded.university.domain.timetable.Timetable;
//
//@Repository
//public interface CustomTimetableRepository extends JpaRepository<Timetable, Integer> {
//    
//    List<Timetable> findByGroup(Group group);
//    
//    List<Timetable> findByTeacher(Teacher teacher);
//    
//}
