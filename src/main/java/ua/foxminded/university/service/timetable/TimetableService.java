package ua.foxminded.university.service.timetable;

import java.util.List;

import ua.foxminded.university.domain.Group;
import ua.foxminded.university.domain.Teacher;
import ua.foxminded.university.domain.timetable.Timetable;
public interface TimetableService {

    public List<Timetable> findAll();

    public Timetable findById(int timetableId);

    public void save (Timetable timetable);

    public void update (int timetableId, Timetable updatedTimetable);

    public void delete (int timetableId);
}
