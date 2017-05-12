package com.romanov.controllers.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.romanov.model.User;
import com.romanov.model.UserStudent;
import com.romanov.model.UserTeacher;
import com.romanov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Kirill on 07.05.2017.
 */
@RestController
public class UserRegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/rest/student-registration", method = RequestMethod.POST)
    public HttpStatus studentRegistration(@RequestBody JsonNode json) throws Exception {
        User user = new User();
        UserStudent userStudent = new UserStudent();
        user.setUserName(json.findPath("userName").asText());
        user.setName(json.findPath("firstName").asText());
        user.setSurname(json.findPath("lastName").asText());
        user.setEmail(json.findPath("email").asText());
        user.setPatronymic(json.findPath("patronymic").asText());
        user.setPassword(passwordEncoder.encode(json.findPath("password").asText()));
        userStudent.setGroup(json.findPath("group").asInt());
        userService.createNewStudent(user, userStudent);
        return HttpStatus.CREATED;
    }

    @RequestMapping(value = "/rest/teacher-registration", method = RequestMethod.POST)
    public HttpStatus teacherRegistration(@RequestBody JsonNode json) throws Exception {
        User user = new User();
        UserTeacher userTeacher = new UserTeacher();
        user.setUserName(json.findPath("userName").asText());
        user.setName(json.findPath("firstName").asText());
        user.setSurname(json.findPath("lastName").asText());
        user.setEmail(json.findPath("email").asText());
        user.setPatronymic(json.findPath("patronymic").asText());
        user.setPassword(passwordEncoder.encode(json.findPath("password").asText()));
        userTeacher.setIdCathedra(json.findPath("cathedra").asInt());
        userTeacher.setPost(json.findPath("post").asText());
        userTeacher.setAcademicDegree(json.findPath("academicDegree").asText());
        userTeacher.setAcademicTitle(json.findPath("academicTitle").asText());
        userService.createNewTeacher(user, userTeacher);
        return HttpStatus.CREATED;
    }

    @RequestMapping(value = "/rest/user-by-email", method = RequestMethod.POST)
    @ResponseBody
    public User getUserByEmail(@RequestBody JsonNode jsonNode) {
        User user = userService.getUserByEmail(jsonNode.findPath("email").asText());
        if (user == null)
            return null;
        else
            return user;
    }

    @RequestMapping(value = "/rest/user-by-username", method = RequestMethod.POST)
    @ResponseBody
    public User getUserByUserName(@RequestBody JsonNode jsonNode) {
        User user = userService.getUserByUserName(jsonNode.findPath("userName").asText());
        if (user == null)
            return null;
        else
            return user;
    }

}
