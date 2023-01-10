package ua.foxminded.university.service.departmentcontent;

import java.util.List;
import java.util.Optional;

import ua.foxminded.university.domain.department.DepartmentContent;
import ua.foxminded.university.exception.UserInputException;

public interface DepartmentContentService {
    
    public Optional<DepartmentContent> findDepartmentContentById(int departmentContentId) 
            throws UserInputException;
    
    public List<DepartmentContent> getAll() throws UserInputException;
    
    public void create (int departmentId, int studentId, int groupId, int teacherId, 
            int timetableId) throws UserInputException;
    
    public void update (int departmentContentId, int departmentId, int studentId,
            int groupId, int teacherId, int timetableId) throws UserInputException;
    
    public void delete (int departmentContentId) throws UserInputException; 
}
