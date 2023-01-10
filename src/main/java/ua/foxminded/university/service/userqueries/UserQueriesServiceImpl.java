//package ua.foxminded.university.service.userqueries;
//
//import java.util.List;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Service;
//
//import ua.foxminded.university.domain.department.DepartmentManager;
//import ua.foxminded.university.domain.timetable.Timetable;
//import ua.foxminded.university.exception.UserInputException;
//import ua.foxminded.university.service.group.GroupServiceImpl;
//
//@Service
//public class UserQueriesServiceImpl implements UserQueriesService {
//
//    @Autowired
//    private DepartmentManager departmentManager;
//    private static final Logger log = LogManager.getLogger(GroupServiceImpl.class);
//
//    @Override
//    public Object getStudentDayTimetable(int studentId, String dateString)
//            throws UserInputException {
//        Object timetable = null;
//        try {
//            timetable = departmentManager.getStudentDayTimetable(studentId, dateString);
//        } catch (DataAccessException e) {
//            log.error(e.getMessage(), e);
//        }
//
//        return timetable;
//    }
//
//    @Override
//    public List<Timetable> getStudentMonthTimetable(int studentId, int monthNumber)
//            throws UserInputException{
//        List<Timetable> timetables = null;
//        try {
//            timetables = departmentManager.getStudentMonthTimetable(studentId, monthNumber);
//        } catch (DataAccessException e) {
//            log.error(e.getMessage(), e);
//        }
//
//        return timetables;
//    }
//
//    @Override
//    public Object getLecturerDayTimetable(int cardId, String dateString)
//            throws UserInputException {
//        Object timetable = null;
//        try {
//            timetable = departmentManager.getLecturerDayTimetable(cardId, dateString);
//        } catch (DataAccessException e) {
//            log.error(e.getMessage(), e);
//        }
//
//        return timetable;
//    }
//
//    @Override
//    public List<Timetable> getLecturerMonthTimetable(int cardId, int monthNumber)
//            throws UserInputException {
//        List<Timetable> timetables = null;
//        try {
//            timetables = departmentManager.getLecturerMonthTimetable(cardId, monthNumber);
//        } catch (DataAccessException e) {
//            log.error(e.getMessage(), e);
//        }
//
//        return timetables;
//    }
//}
