package ua.foxminded.university.service.university;

import java.util.List;
import java.util.Optional;

import ua.foxminded.university.domain.Group;
import ua.foxminded.university.domain.university.University;
import ua.foxminded.university.exception.UserInputException;

public interface UniversityService {

    public List<University> findAll();

    public University findById(int departmentId);

    public void save (University university);

    public void update (int departmentId, University updatedUniversity);

    public void delete (int departmentId);
}
