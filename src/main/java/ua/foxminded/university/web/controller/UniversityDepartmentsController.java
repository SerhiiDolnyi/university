package ua.foxminded.university.web.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import ua.foxminded.university.domain.university.University;
import ua.foxminded.university.exception.UserInputException;
import ua.foxminded.university.service.university.UniversityService;

@Controller
public class UniversityDepartmentsController {

    @Autowired
    UniversityService universityService;

    @ModelAttribute("allUniversityDepartments")
    public List<University> populateUniversity() throws UserInputException {
        return this.universityService.findAll();
    }

    @RequestMapping(value = "/universitydepartment", method = RequestMethod.GET)
    public String showUniversityDepartment() {
        return "departments/universitydepartment";
    }

    @GetMapping("/universitydepartment/new")
    public String newGroup(Model model) {
        model.addAttribute("universitydepartment", new University());
        return "departments/new";
    }

    @PostMapping("/universitydepartment")
    public String create(@ModelAttribute("universitydepartment") @Valid University universitydepartment,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "departments/new";
        }
        this.universityService.save(universitydepartment);
        return "redirect:/universitydepartment";
    }

    @GetMapping("/universitydepartment/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws UserInputException {
        University department= universityService.findById(id);
        model.addAttribute("department", department);
        return "departments/edit";
    }

    @PutMapping("/universitydepartment/{id}")
    public String update(@ModelAttribute("department") @Valid University department,
            BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "departments/edit";
        }
        universityService.update(id, department);
        return "redirect:/universitydepartment";
    }

    @DeleteMapping("/universitydepartment/{id}")
    public String delete(@PathVariable("id") int id) throws UserInputException {
        universityService.delete(id);
        return "redirect:/universitydepartment";
    }
}
