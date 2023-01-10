package ua.foxminded.university.service.group;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import ua.foxminded.university.domain.Group;
import ua.foxminded.university.exception.UserInputException;

public interface GroupService {

    public List<Group> findAll();

    public Group findById(int groupId);

    public void save (Group group);

    public void update (int groupId, Group updatedGroup);

    public void delete (int groupId);
}
