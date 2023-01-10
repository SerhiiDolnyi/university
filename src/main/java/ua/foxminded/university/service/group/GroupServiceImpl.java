package ua.foxminded.university.service.group;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ua.foxminded.university.domain.Group;
import ua.foxminded.university.repositories.GroupRepository;

@Service
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService {
    GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> findAll(){
        return groupRepository.findAll();
    }

    @Override
    public Group findById(int groupId){
        Optional<Group> foundGroup = groupRepository.findById(groupId);
        return foundGroup.orElse(null);
    }

    @Override
    @Transactional
    public void save (Group group){
        groupRepository.save(group);
    }

    @Override
    @Transactional
    public void update (int groupId, Group updatedGroup){
           updatedGroup.setGroupId(groupId);
           groupRepository.save(updatedGroup);
    }

    @Override
    @Transactional
    public void delete (int groupId){
        groupRepository.deleteById(groupId);
    }
}
