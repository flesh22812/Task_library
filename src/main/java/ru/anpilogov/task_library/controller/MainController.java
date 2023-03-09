package ru.anpilogov.task_library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "index";
    }
    @GetMapping("/main")
    public String main() {
        return "main";
    }


    @GetMapping("/admin")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/swagger-ui/index.html#/", model);
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

}
