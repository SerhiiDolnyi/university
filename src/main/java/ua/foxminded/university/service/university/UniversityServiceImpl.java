package ua.foxminded.university.service.university;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ua.foxminded.university.domain.university.University;
import ua.foxminded.university.repositories.UniversityRepository;

@Service
@Transactional(readOnly = true)
public class UniversityServiceImpl implements UniversityService {
    UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public List<University> findAll(){
        return universityRepository.findAll();
    }

    @Override
    public University findById(int departmentId){
        Optional<University> foundDepartment = universityRepository.findById(departmentId);
        return foundDepartment.orElse(null);
    }

    @Override
    @Transactional
    public void save (University university){
        universityRepository.save(university);
    }

    @Override
    @Transactional
    public void update (int departmentId, University updatedUniversity){
        updatedUniversity.setDepartmentId(departmentId);
        universityRepository.save(updatedUniversity);
    }

    @Override
    @Transactional
    public void delete (int departmentId){
        universityRepository.deleteById(departmentId);
    }
}
