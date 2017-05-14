package com.romanov.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Kirill on 05.03.2017.
 */
@Controller
@Scope ("session")
public class LoginController {

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login() throws Exception {
        return "login";
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.POST)
    public String loginCheck() throws Exception {
        return "redirect:/home";
    }
}
