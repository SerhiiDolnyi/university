package ua.foxminded.university.web.controller;

import java.lang.reflect.Array;
import java.sql.Date;
import java.sql.Time;
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
import ua.foxminded.university.domain.Student;
import ua.foxminded.university.domain.Teacher;
import ua.foxminded.university.domain.timetable.Timetable;
import ua.foxminded.university.domain.university.University;
import ua.foxminded.university.exception.UserInputException;
import ua.foxminded.university.service.group.GroupService;
import ua.foxminded.university.service.group.GroupServiceImpl;
import ua.foxminded.university.service.student.StudentService;
import ua.foxminded.university.service.teacher.TeacherService;
import ua.foxminded.university.service.teacher.TeacherServiceImpl;
import ua.foxminded.university.service.timetable.TimetableService;
import ua.foxminded.university.service.timetable.TimetableServiceImpl;
import ua.foxminded.university.service.university.UniversityService;
import ua.foxminded.university.service.university.UniversityServiceImpl;

@Controller
public class TimetablesController {
    @Autowired
    TimetableService timetableService;
    @Autowired
    GroupService groupService;
    @Autowired
    TeacherService teacherService;

    @ModelAttribute("allTimetables")
    public List<Timetable> populateTimetables() {
        return this.timetableService.findAll();
    }

    @ModelAttribute("groups")
    public List<Group> populateGroupIds() {
        return groupService.findAll();
    }

    @ModelAttribute("teachers")
    public List<Teacher> populateTeacherIds() {
        return teacherService.findAll();
    }

    @GetMapping(value = "/timetable")
    public String showTimetables() {       
        return "timetables/timetable";
    }    

    @GetMapping("/timetable/new")
    public String newTimetableService(Model model) {
        model.addAttribute("timetable", new Timetable());

        return "timetables/new";
    }

    @PostMapping("/timetable")
    public String create(
            @RequestParam(name="date") Date date,
            @RequestParam(name="startLecture") Time startLecture,
            @RequestParam(name="endLecture") Time endLecture,
            @RequestParam(name="location") String location,
            @RequestParam(name="groupId") int groupId,
            @RequestParam(name="subject") String subject,
            @RequestParam(name="teacherId") int teacherId
            ) {
        Group group = groupService.findById(groupId);
        Teacher teacher = teacherService.findById(teacherId); 
        Timetable timetable = new Timetable(date, startLecture, endLecture, location, group, subject, teacher);
        this.timetableService.save(timetable);
        return "redirect:/timetable";
    }
    
    @GetMapping("/timetable/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        Timetable timetable = timetableService.findById(id);        
        model.addAttribute("timetable", timetable);

        return "timetables/edit";
    }

    @PutMapping("/timetable/{id}")
    public String update(@PathVariable("id") int id,
            @RequestParam(name="date") Date date,
            @RequestParam(name="startLecture") Time startLecture,
            @RequestParam(name="endLecture") Time endLecture,
            @RequestParam(name="location") String location,
            @RequestParam(name="groupId") int groupId,
            @RequestParam(name="subject") String subject,
            @RequestParam(name="teacherId") int teacherId
            ) {
        Group group = groupService.findById(groupId);
        Teacher teacher = teacherService.findById(teacherId);
        Timetable timetable = new Timetable(date, startLecture, endLecture, location, group, subject, teacher);
        timetableService.update(id, timetable);

        return "redirect:/timetable";
    }

    @DeleteMapping("/timetable/{id}")
    public String delete(@PathVariable("id") int id) {
        timetableService.delete(id);
        return "redirect:/timetable";
    }
    
    @GetMapping(value = "/groupTimetable")
    public String showGroupTimetables(@RequestParam(name = "groupId") int groupId, Model model) {
        Group group = groupService.findById(groupId);
        List<Timetable> groupTimetables = group.getTimetables();
        model.addAttribute("groupTimetables", groupTimetables);
        
        return "timetables/groupTimetable";
    }
    
  @GetMapping(value = "/teacherTimetable")
  public String showTeacherTimetables(@RequestParam(name = "teacherId") int teacherId, Model model) {
      Teacher teacher = teacherService.findById(teacherId);
      List<Timetable> teacherTimtables = teacher.getTimetables();
      model.addAttribute("teacherTimtables", teacherTimtables);
      
      return "timetables/teacherTimetable";
  }
}
