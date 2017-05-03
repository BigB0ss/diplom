package com.romanov.controllers;


import com.romanov.model.Discipline;
import com.romanov.model.Hardware;
import com.romanov.model.Software;
import com.romanov.model.Type;
import com.romanov.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    private SoftwareRepository softwareRepository;

    @Autowired
    private HardwareRepository hardwareRepository;

    @RequestMapping(value = "/home")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/home/create-technical-task", method = RequestMethod.GET)
    public String createTechnicalTask(Model model) {
        List<Type> types= typeTechnicalTaskRepository.getAllTypes();
        List<Discipline> disciplines= disciplineRepository.getAll();
        model.addAttribute("types",types);
        model.addAttribute("disciplines",disciplines);
        return "create_new_tt";
    }


}
