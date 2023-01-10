package ua.foxminded.university.domain.department;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface DepartmentManager <T> {
    
    public T  getStudentDayTimetable(int studentId, String dateString);

    public List<?> getStudentMonthTimetable(int studentId, int monthNumber);

    public T getLecturerDayTimetable(int cardId, String dateString);

    public List<?> getLecturerMonthTimetable(int cardId, int monthNumber);
}
