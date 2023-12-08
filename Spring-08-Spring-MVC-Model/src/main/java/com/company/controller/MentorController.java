package com.company.controller;

import com.company.model.Mentor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mentor")
public class MentorController {

    @RequestMapping("/list") // http://localhost:8080/mentor/list
    public String showTable(Model model){

        List<Mentor> mentorList = new ArrayList<>();
        mentorList.add(new Mentor("Anna", "Sand", 35, "Female"));
        mentorList.add(new Mentor("John", "Davis", 30, "Male"));
        mentorList.add(new Mentor("Ellen", "River", 33, "Female"));
        mentorList.add(new Mentor("Jessica", "Forest", 50, "Female"));

        model.addAttribute("mentors", mentorList);

        return "mentor/mentor-list";
    }
}
