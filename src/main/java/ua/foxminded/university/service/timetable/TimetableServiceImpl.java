package ua.foxminded.university.service.timetable;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.foxminded.university.domain.Group;
import ua.foxminded.university.domain.Teacher;
import ua.foxminded.university.domain.timetable.Timetable;
import ua.foxminded.university.repositories.GroupRepository;
import ua.foxminded.university.repositories.TeacherRepository;
import ua.foxminded.university.repositories.TimetableRepository;

@Service
@Transactional(readOnly = true)
public class TimetableServiceImpl implements TimetableService {
    TimetableRepository timetableRepository;
    GroupRepository groupRepository;
    TeacherRepository teacherRepository;

    @Autowired           
    public TimetableServiceImpl(TimetableRepository timetableRepository, GroupRepository groupRepository,
            TeacherRepository teacherRepository) {
        this.timetableRepository = timetableRepository;
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Timetable> findAll(){
        return timetableRepository.findAll();
    }

    @Override
    public Timetable findById(int timetableId){
        Optional<Timetable> foundTimetable = timetableRepository.findById(timetableId);
        return foundTimetable.orElse(null);
    }

    @Override
    @Transactional
    public void save (Timetable timetable){
        timetableRepository.save(timetable);
    }

    @Override
    @Transactional
    public void update (int timetableId, Timetable updatedTimetable){
        updatedTimetable.setTimetableId(timetableId);
           timetableRepository.save(updatedTimetable);
    }

    @Override
    @Transactional
    public void delete (int timetableId){
        timetableRepository.deleteById(timetableId);
    }    
}
