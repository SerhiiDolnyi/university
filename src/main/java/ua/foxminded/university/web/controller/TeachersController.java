package ua.foxminded.university.web.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.foxminded.university.domain.Group;
import ua.foxminded.university.domain.Position;
import ua.foxminded.university.domain.Student;
import ua.foxminded.university.domain.Teacher;
import ua.foxminded.university.domain.university.University;
import ua.foxminded.university.exception.UserInputException;
import ua.foxminded.university.service.student.StudentService;
import ua.foxminded.university.service.teacher.TeacherService;
import ua.foxminded.university.service.university.UniversityService;
import ua.foxminded.university.service.university.UniversityServiceImpl;

@Controller
public class TeachersController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    UniversityService universityService;

    @ModelAttribute("allTeachers")
    public List<Teacher> populateTeacher() {
        return this.teacherService.findAll();
    }


    @ModelAttribute("departments")
    public List<University> populateDepartments() {
        return  universityService.findAll();
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public String showTeachers() {
        return "teachers/teacher";
    }

    @GetMapping("/teacher/new")
    public String newTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());

        return "teachers/new";
    }
    
    @PostMapping("/teacher")
    public String create(@ModelAttribute("teacher") @Valid Teacher teacher, 
            @RequestParam(name = "departmentId") int departmentId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "teachers/new";
        }
        University department = universityService.findById(departmentId);
        teacher.setDepartment(department);
        this.teacherService.save(teacher);

        return "redirect:/teacher";
    }

    @GetMapping("/teacher/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);

        return "teachers/edit";
    }

    @PutMapping("/teacher/{id}")
    public String update(@ModelAttribute("teacher") @Valid Teacher teacher, 
            BindingResult bindingResult, @PathVariable("id") int id, 
            @RequestParam(name = "departmentId") int departmentId) {
        if (bindingResult.hasErrors()) {
            return "teachers/edit";
        }
        University department = universityService.findById(departmentId);
        teacher.setDepartment(department);
        teacherService.update(id, teacher);

        return "redirect:/teacher";
    }

    @DeleteMapping("/teacher/{id}")
    public String delete(@PathVariable("id") int id) {
        teacherService.delete(id);

        return "redirect:/teacher";
    }
}
