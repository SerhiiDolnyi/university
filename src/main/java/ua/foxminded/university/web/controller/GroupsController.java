package ua.foxminded.university.web.controller;
import java.util.List;
import java.util.Optional;
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

import ua.foxminded.university.domain.Group;
import ua.foxminded.university.exception.UserInputException;
import ua.foxminded.university.service.group.GroupService;
import ua.foxminded.university.service.group.GroupServiceImpl;


@Controller
public class GroupsController {
    
    @Autowired
    GroupService groupService;
    
    @ModelAttribute("allGroups")
    public List<Group> populateGroup() {
        return this.groupService.findAll();
    }
    
    @GetMapping("/group")
    public String showGroup() {        

        return "groups/group";
    }
    
    @GetMapping("/group/new")
    public String newGroup(Model model) {
        model.addAttribute("group", new Group());
        return "groups/new";
    }
     
    @PostMapping("/group")
    public String create(@ModelAttribute("group") @Valid Group group, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "groups/new";
        this.groupService.save(group);

        return "redirect:/group";
    }
    
    @GetMapping("/group/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        Group group = groupService.findById(id);
        model.addAttribute("group", group);
        return "groups/edit";
    }
    
    @PutMapping("/group/{id}")
    public String update(@ModelAttribute("group") @Valid Group group, BindingResult bindingResult,
            @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "groups/edit";
        groupService.update(id, group);

        return "redirect:/group";
    }

    @DeleteMapping("/group/{id}")
    public String delete(@PathVariable("id") int id) {
        groupService.delete(id);
        return "redirect:/group";
    }
}
