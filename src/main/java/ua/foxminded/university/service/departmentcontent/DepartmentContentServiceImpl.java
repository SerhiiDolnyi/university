//package ua.foxminded.university.service.departmentcontent;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Service;
//
//import ua.foxminded.university.dao.department.DepartmentContentDao;
//import ua.foxminded.university.dao.department.JdbcDepartmentContentDaoImpl;
//import ua.foxminded.university.domain.department.DepartmentContent;
//import ua.foxminded.university.exception.UserInputException;
//
//@Service
//public class DepartmentContentServiceImpl implements DepartmentContentService {
//
//    @Autowired
//    DepartmentContentDao departmentContentDao;
//    private static final Logger log = LogManager.getLogger(JdbcDepartmentContentDaoImpl.class);
//
//    @Override
//    public Optional<DepartmentContent> findDepartmentContentById(int departmentContentId)
//            throws UserInputException {
//        Optional<DepartmentContent> departmentContent = null;
//        try {
//            departmentContent = departmentContentDao.findDepartmentContentById(departmentContentId);
//        } catch( DataAccessException e) {
//            log.error(e.getMessage(), e);
//        }
//        return departmentContent;
//    }
//
//    @Override
//    public List<DepartmentContent> getAll() throws UserInputException {
//        List<DepartmentContent> departmentContents = null;
//        try {
//            departmentContents = departmentContentDao.getAll();
//        } catch( DataAccessException e) {
//            log.error(e.getMessage(), e);
//        }
//        return departmentContents;
//    }
//
//    @Override
//    public void create(int departmentId, int studentId, int groupId, int teacherId,
//            int timetableId) throws UserInputException {
//        try {
//            departmentContentDao.create(departmentId, studentId, groupId, teacherId, timetableId);
//        } catch( DataAccessException e) {
//            log.error(e.getMessage(), e);
//        }
//        log.debug("departmentContent successfully created. ");
//    }
//
//    @Override
//    public void update(int departmentContentId, int departmentId, int studentId,
//            int groupId, int teacherId, int timetableId) throws UserInputException {
//        try {
//            departmentContentDao.update(departmentContentId, departmentId, studentId,
//                    groupId, teacherId, timetableId);
//        } catch( DataAccessException e) {
//            log.error(e.getMessage(), e);
//        }
//        log.debug("departmentContent with ID: {} successfully updated.\n", departmentContentId);
//    }
//
//    @Override
//    public void delete(int departmentContentId) throws UserInputException {
//        try {
//            departmentContentDao.delete(departmentContentId);
//        } catch( DataAccessException e) {
//            log.error(e.getMessage(), e);
//        }
//        log.debug("DepartmentContentId with ID: {} successfully deleted.\n", departmentContentId);
//    }
//}
