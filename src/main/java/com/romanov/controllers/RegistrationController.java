package com.romanov.controllers;

import com.romanov.model.Group;
import com.romanov.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Kirill on 05.03.2017.
 */
@Controller
public class RegistrationController {

    @Autowired
    private GroupRepository groupRepository;

    @RequestMapping(value = "/registration")
    public String registration() {
        return "registration";
    }

    @RequestMapping(value = "/registration-student")
    public String registrationStudent(Model model) {
        List<Group> groupList = groupRepository.getAllGroup();
        model.addAttribute("groups",groupList);
        return "registrationForStudent";
    }

    @RequestMapping(value = "/registration-teacher")
    public String registrationTeacher() {
        return "registrationForTeacher";
    }


}
