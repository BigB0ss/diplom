package com.romanov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Kirill on 05.03.2017.
 */
@Controller
public class LoginController {

    @RequestMapping( value = "/", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping( value = "/", method = RequestMethod.POST)
    public String loginCheck(){
        return "redirect:/home";
    }
}
