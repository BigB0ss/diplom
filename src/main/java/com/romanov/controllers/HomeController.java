package com.romanov.controllers;


import com.romanov.model.*;
import com.romanov.repository.*;
import com.romanov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirill on 05.03.2017.
 */

@Controller
@Scope("session")
public class HomeController {

    @Autowired
    private TechnicalTaskRepository technicalTaskRepository;

    @Autowired
    private TypeTechnicalTaskRepository typeTechnicalTaskRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        User currentUser = userService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<TechnicalTask> technicalTasks = technicalTaskRepository.getAllTaskForUser(currentUser);
        List<TechnicalTaskForUserDomain> technicalTaskForUserDomains = mapTechnicalTaskForUser(technicalTasks);
        model.addAttribute("technicalTasks", technicalTaskForUserDomains);
        return "home";
    }

    private List<TechnicalTaskForUserDomain> mapTechnicalTaskForUser(List<TechnicalTask> technicalTasks) {
        List<TechnicalTaskForUserDomain> technicalTaskForUserDomains = new ArrayList<>();
        for (TechnicalTask technicalTask : technicalTasks) {
            TechnicalTaskForUserDomain technicalTaskForUserDomain = new TechnicalTaskForUserDomain();
            technicalTaskForUserDomain.setId(technicalTask.getId());
            technicalTaskForUserDomain.setName(technicalTask.getName());
            technicalTaskForUserDomain.setTarget(technicalTask.getTarget());
            technicalTaskForUserDomain.setDateCompleted(technicalTask.getDateComplted());
            technicalTaskForUserDomain.setDateCreated(technicalTask.getDateCreated());
            technicalTaskForUserDomain.setDemands(technicalTask.getDemands());
            technicalTaskForUserDomain.setTasks(technicalTask.getTasks());
            technicalTaskForUserDomain.setTypeTechnicalTask(typeTechnicalTaskRepository.getTypeById((long) technicalTask.getTypeTechnicalTask()).getType());
            technicalTaskForUserDomain.setDiscipline(disciplineRepository.getDisciplineById((long) technicalTask.getDiscipline()).getDescription());
            technicalTaskForUserDomains.add(technicalTaskForUserDomain);
        }
        return technicalTaskForUserDomains;
    }


    @RequestMapping(value = "/home/create-technical-task", method = RequestMethod.GET)
    public String createTechnicalTask(Model model) {
        List<Type> types = typeTechnicalTaskRepository.getAllTypes();
        List<Discipline> disciplines = disciplineRepository.getAll();
        model.addAttribute("types", types);
        model.addAttribute("disciplines", disciplines);
        return "create_new_tt";
    }


}
