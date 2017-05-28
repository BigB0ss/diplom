package com.romanov.controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.romanov.model.*;
import com.romanov.repository.*;
import com.romanov.service.UserService;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.poi.xssf.usermodel.XSSFTextParagraph;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            technicalTaskForUserDomain.setTheme(technicalTask.getTheme());
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
        model.addAttribute("theme", technicalTask.getTheme());
        model.addAttribute("tasks", technicalTask.getTasks());
        model.addAttribute("name", technicalTask.getName());
        model.addAttribute("target", technicalTask.getTarget());
        model.addAttribute("demands", technicalTask.getDemands());
        model.addAttribute("types", types);
        model.addAttribute("disciplines", disciplines);
        return "updateTechnicalTask";
    }

    @RequestMapping(value = "/download/technical-task")
    public void downloadTechnicalTask(@RequestParam("id") int id, HttpServletResponse response) throws XmlException {

        TechnicalTask technicalTask = technicalTaskRepository.getTechnicalTaskById(id);
        List<TechnicalTask> list = new ArrayList<>();
        list.add(technicalTask);
        List<TechnicalTaskForUserDomain> technicalTaskForUserDomains = mapTechnicalTaskForUser(list);
        XWPFDocument document = new XWPFDocument();
        CTAbstractNum cTAbstractNum = CTAbstractNum.Factory.newInstance();
        //Next we set the AbstractNumId. This requires care.
        //Since we are in a new document we can start numbering from 0.
        //But if we have an existing document, we must determine the next free number first.
        cTAbstractNum.setAbstractNumId(BigInteger.valueOf(0));

///* Bullet list
        CTLvl cTLvl = cTAbstractNum.addNewLvl();
        cTLvl.addNewNumFmt().setVal(STNumberFormat.DECIMAL);
        cTLvl.addNewLvlText().setVal("•");
//*/

/* Decimal list
  CTLvl cTLvl = cTAbstractNum.addNewLvl();
  cTLvl.addNewNumFmt().setVal(STNumberFormat.DECIMAL);
  cTLvl.addNewLvlText().setVal("%1.");
  cTLvl.addNewStart().setVal(BigInteger.valueOf(1));
*/

        XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);

        XWPFNumbering numbering = document.createNumbering();

        BigInteger abstractNumID = numbering.addAbstractNum(abstractNum);

        BigInteger numID = numbering.addNum(abstractNumID);





        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run.setFontSize(12);
        run.setText("ТЕХНИЧЕСКОЕ ЗАДАНИЕ");
        paragraph.setSpacingAfter(0);

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run.setFontSize(12);
        String str = technicalTaskForUserDomains.get(0).getTypeTechnicalTask().equals("Курсовая") ? "на курсовую работу" : "на ВКР";
        run.setText(str);
        paragraph.setSpacingAfter(0);

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run.setFontSize(12);
        run.setText("по дисциплине \"" + technicalTaskForUserDomains.get(0).getDiscipline() + "\"");
        paragraph.setSpacingAfter(0);

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run.setFontSize(12);
        run.setText("на тему \"" + technicalTaskForUserDomains.get(0).getTheme() + "\"");
        XWPFNumbering xwpfNumbering = document.createNumbering();

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        paragraph.setNumID(new BigInteger("10"));
        run.setText("ЦЕЛЬ РАБОТЫ");
        run.addBreak();
        run.setText(technicalTask.getTarget());

        run.addBreak();

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        paragraph.setNumID(new BigInteger("10"));
        System.out.println(paragraph.getNumIlvl());
        run.setText("ОСНОВНЫЕ ЗАДАЧИ");

        XWPFTable table = document.createTable();
        CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();
        width.setType(STTblWidth.DXA);

        width.setW(BigInteger.valueOf(9072));

        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("Задача");
        tableRowOne.addNewTableCell().setText("Срок исполнения");

        for (Task t : technicalTask.getTasks()) {
            XWPFTableRow newRow = table.createRow();
            newRow.setHeight(100);
            newRow.getCell(0).getCTTc();
            newRow.getCell(0).setText(t.getDescription());
            newRow.getCell(1).setText(new SimpleDateFormat("dd.MM.YYYY").format(t.getDate()));
        }

        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addBreak();

        long counter = 1;
        for (Map.Entry<String, List<String>> demand : technicalTask.getDemands().entrySet()) {
            paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.LEFT);
            paragraph.setNumID(new BigInteger("10"));
            paragraph.setSpacingBefore(10);
            run = paragraph.createRun();
            run.setCapitalized(true);
            run.setText(demand.getKey());
            for (String d : demand.getValue()) {
                XWPFParagraph subparagraph = document.createParagraph();
                subparagraph.setAlignment(ParagraphAlignment.CENTER);
                subparagraph.setNumID(numID);
                run = subparagraph.createRun();
                run.setText(d);
            }
            counter++;
        }
        run.addBreak();
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("Руководитель");
        paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);
        run.addBreak();


        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setText("Задание получил студент");
        paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);
        run.addBreak();
        try {
            FileOutputStream out = new FileOutputStream(new File("tt.docx"));
            document.write(out);
            out.close();
            InputStream is = new FileInputStream(new File("tt.docx"));

            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {

        } finally {
        }

        System.out.println("createdocument.docx written successully");
    }

}
