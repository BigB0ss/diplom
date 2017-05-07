package com.romanov.controllers.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.romanov.model.User;
import com.romanov.model.UserStudent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Kirill on 07.05.2017.
 */
@RestController
public class UserRegistrationController {

    @RequestMapping(value = "/rest/student-registration", method = RequestMethod.POST)
    public HttpStatus studentRegistration(@RequestBody JsonNode json) {
        System.out.println(json);

        User user = new User();
        UserStudent userStudent = new UserStudent();

        user.setName(json.findPath("firstName").asText());
        user.setSurname(json.findPath("lastName").asText());
        user.setEmail(json.findPath("email").asText());
        user.setPatronynic(json.findPath("patronymic").asText());
        user.setPassword(json.findPath("password").asText());
        userStudent.setGroup(json.findPath("group").asInt());

        return HttpStatus.CREATED;
    }
}
