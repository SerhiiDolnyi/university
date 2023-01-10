package ua.foxminded.university.web.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    private static final Logger log = LogManager.getLogger(HomeController.class);
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home(Model model, HttpSession session, HttpServletRequest request) {
        log.debug("request made to /index");        
        String viewPage = "index";               
        
        return viewPage;
    }
    
    @RequestMapping(value = "/")
    public String root() {

        return "redirect:index";       
    }    
                
}
