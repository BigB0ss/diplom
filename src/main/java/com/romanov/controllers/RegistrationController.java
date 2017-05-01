package com.romanov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kirill on 05.03.2017.
 */
@Controller
public class RegistrationController {


    @RequestMapping(value = "/registration")
    public String registration(){
        return "registration";
    }

    @RequestMapping(value = "/registration-student")
    public String registrationStudent(){
        return "registrationForStudent";
    }

    @RequestMapping(value = "/registration-teacher")
    public String registrationTeacher(){
        return "registrationForTeacher";
    }


}
