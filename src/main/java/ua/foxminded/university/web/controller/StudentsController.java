package ua.foxminded.university.web.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import ua.foxminded.university.exception.UserInputException;
import ua.foxminded.university.service.group.GroupService;
import ua.foxminded.university.service.student.StudentService;
import ua.foxminded.university.domain.Group;
import ua.foxminded.university.domain.Student;
import ua.foxminded.university.domain.Teacher;
import ua.foxminded.university.domain.university.University;

@Controller
public class StudentsController {

    @Autowired
    StudentService studentService;

    @Autowired
    GroupService groupService;

    @ModelAttribute("allstudents")
    public List<Student> populateAllStudents() {
        List<Student> students = studentService.findAll();
        return students;
    }
    
    @ModelAttribute("groups")
    public List<Group> populateGroups() {
        return groupService.findAll();
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String showAllStudents() {
        return "students/student";
    }

    @GetMapping("/student/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "students/new";
    }

    @PostMapping("/student")
    public String create(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult,
                        @RequestParam(name = "groupId") int groupId
                        ) {
        if (bindingResult.hasErrors())
            return "students/new";
        Group group = groupService.findById(groupId);
        student.setGroup(group);
        this.studentService.save(student);

        return "redirect:/student";
    }

    @GetMapping("/student/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws UserInputException {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);

        return "students/edit";
    }

    @PutMapping("/student/{id}")
    public String update(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult,
            @PathVariable("id") int id, @RequestParam(name = "groupId") int groupId) {
        if (bindingResult.hasErrors())
            return "students/edit";
        Group group = groupService.findById(groupId);
        student.setGroup(group);
        studentService.update(id, student);

        return "redirect:/student";
    }

    @DeleteMapping("/student/{id}")
    public String delete(@PathVariable("id") int id) {
        studentService.delete(id);
        return "redirect:/student";
    }
}
