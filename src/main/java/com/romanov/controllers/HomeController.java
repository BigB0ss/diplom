package com.romanov.controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.romanov.model.*;
import com.romanov.repository.*;
import com.romanov.service.UserService;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private SignaturesRepository signaturesRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        User currentUser = userService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<TechnicalTask> technicalTasks = technicalTaskRepository.getAllTaskForUser(currentUser);
        List<TechnicalTaskForUserDomain> technicalTaskForUserDomains = mapTechnicalTaskForUser(technicalTasks);
        List<Group> groups = groupRepository.getAllGroup();
        List<UserStudent> allStudents = userRepository.getAllStudent();
        model.addAttribute("technicalTasks", technicalTaskForUserDomains);
        model.addAttribute("students", allStudents);
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
            technicalTaskForUserDomain.setAppointemnt(technicalTaskRepository.checkApointemnt(technicalTask.getId()));
            technicalTaskForUserDomain.setUserStudent(new UserStudent());
            User user = null;
            if (signaturesRepository.getIdStudent(technicalTask.getId()) != 0) {
                user = userRepository.getUserByUserId(signaturesRepository.getIdStudent(technicalTask.getId()));
            }
            technicalTaskForUserDomain.getUserStudent().setUser(user);
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

    @RequestMapping(value = "/home/update-technical-task", method = RequestMethod.GET)
    public String updateTechnicalTask(Model model, @RequestParam("id") int id) {
        List<Type> types = typeTechnicalTaskRepository.getAllTypes();
        List<Discipline> disciplines = disciplineRepository.getAll();
        User currentUser = userService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        //int id = json.findPath("id").asInt();
        TechnicalTask technicalTask = technicalTaskRepository.getTechnicalTaskById(id);
        model.addAttribute("id", id);
        model.addAttribute("techncalTask", technicalTask);
        model.addAttribute("tasks", technicalTask.getTasks());
        model.addAttribute("name", technicalTask.getName());
        model.addAttribute("target", technicalTask.getTarget());
        model.addAttribute("demands", technicalTask.getDemands());
        model.addAttribute("types", types);
        model.addAttribute("disciplines", disciplines);
        return "updateTechnicalTask";
    }

    @RequestMapping(value = "/download/technical-task")
    public void downloadTechnicalTask(@RequestParam("id") int id) {
        TechnicalTask technicalTask = technicalTaskRepository.getTechnicalTaskById(id);
        List<TechnicalTask> list = new ArrayList<>();
        list.add(technicalTask);
        List<TechnicalTaskForUserDomain> technicalTaskForUserDomains = mapTechnicalTaskForUser(list);
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run.setFontSize(12);
        run.setText("ТЕХНИЧЕСКОЕ ЗАДАНИЕ");
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run.setFontSize(12);
        run.setText("на " + technicalTaskForUserDomains.get(0).getTypeTechnicalTask());
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run.setFontSize(12);
        run.setText("по дисциплине \"" + technicalTaskForUserDomains.get(0).getDiscipline() + "\"");
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        paragraph.setNumID(new BigInteger("1"));
        run.setText("ЦЕЛЬ РАБОТЫ");
        run.addBreak();
        run.setText(technicalTask.getTarget());
        run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        paragraph.setNumID(new BigInteger("2"));
        run.setText("ОСНОВНЫЕ ЗАДАЧИ");
        try {
            FileOutputStream out = new FileOutputStream(new File("tt.docx"));
            document.write(out);
            out.close();
        } catch (Exception e) {

        }

        System.out.println("createdocument.docx written successully");
    }

}
