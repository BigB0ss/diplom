package com.romanov.controllers.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import com.romanov.model.Task;
import com.romanov.model.TechnicalTask;

import com.romanov.model.User;
import com.romanov.repository.SignaturesRepository;
import com.romanov.repository.TechnicalTaskRepository;
import com.romanov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.*;

import java.util.stream.Collectors;

/**
 * Created by Kirill_Romanov1 on 28-Mar-17.
 */

@RestController
@Scope("session")
public class TechnicalTaskRestController {

    @Autowired
    private TechnicalTaskRepository technicalTaskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SignaturesRepository signaturesRepository;

    @RequestMapping(value = "/rest/technical-task", method = RequestMethod.POST)
    public HttpStatus addTecgnicalTask(@RequestBody JsonNode json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader reader = objectMapper.readerFor(new TypeReference<List<List<String>>>() {
        });
        ObjectReader reader1 = objectMapper.readerFor(new TypeReference<List<String>>() {
        });
        ObjectReader reader2 = objectMapper.readerFor(new TypeReference<List<Date>>() {
        });

        TechnicalTask task = new TechnicalTask();
        task.setName(json.findPath("name").asText());
        task.setTheme(json.findPath("theme").asText());
        task.setDateCreated(new Date());
        task.setTarget(json.findPath("target").asText());
        task.setDiscipline(json.findPath("discipline").asInt());
        task.setTypeTechnicalTask(json.findPath("type").asInt());
        List<String> description = reader1.readValue(json.findPath("tasks"));
        task.setTaskTime(reader2.readValue(json.findPath("tasksTime")));

        List<Task> tasks = new ArrayList<>();
        description.stream().forEach(d -> {
            Task t = new Task();
            t.setDescription(d);
            tasks.add(t);
        });
        final int[] counter = {0};
        task.getTaskTime().stream().forEach(tt -> {
            tasks.get(counter[0]).setDate(tt);
            counter[0]++;
        });
        task.setTasks(tasks);
        List<List<String>> demandDescription = new ArrayList<>();
        List<String> demand = new ArrayList<>();
        List<List<String>> claims = new ArrayList<>();
        String str = json.findPath("claims").toString();
        List<List<String>> list = reader.readValue(json.findPath("claims"));
        for (List<String> elem : list) {
            demand.add(elem.get(0));
        }
        demandDescription = list.stream().peek(elem -> elem.remove(0)).collect(Collectors.toList());
        Map<String, List<String>> demandsMap = new LinkedHashMap<>();
        for (int i = 0; i < demand.size(); i++) {
            demandsMap.put(demand.get(i), demandDescription.get(i));
        }
        task.setDemands(demandsMap);
        technicalTaskRepository.addTechnicalTask(task);
        return HttpStatus.CREATED;

    }

    @RequestMapping(value = "/rest/technical-task", method = RequestMethod.PUT)
    TechnicalTask updateTechTask(@RequestBody JsonNode json) {
        User currentUser = userService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        int id = json.findPath("id").asInt();
        TechnicalTask technicalTask = technicalTaskRepository.getTechnicalTaskById(id);
        return technicalTask;
    }

    @RequestMapping(value = "rest/appoint-technical-task", method = RequestMethod.POST)
    public HttpStatus appointTechnicalTask(@RequestBody JsonNode json) {
        signaturesRepository.addSignaturesForStudent(json.findPath("idTechnicalTask").asInt(), json.findPath("idStudent").asInt());
        return HttpStatus.CREATED;
    }

    @RequestMapping(value = "/rest/update-technical-task", method = RequestMethod.POST)
    public HttpStatus updateTechnicalTask(@RequestBody JsonNode json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader reader = objectMapper.readerFor(new TypeReference<List<List<String>>>() {
        });
        ObjectReader reader1 = objectMapper.readerFor(new TypeReference<List<String>>() {
        });
        ObjectReader reader2 = objectMapper.readerFor(new TypeReference<List<Date>>() {
        });

        TechnicalTask task = new TechnicalTask();
        task.setId(json.findPath("id").asLong());
        task.setName(json.findPath("name").asText());
        task.setDateCreated(new Date());
        task.setTheme(json.findPath("theme").asText());
        task.setTarget(json.findPath("target").asText());
        task.setDiscipline(json.findPath("discipline").asInt());
        task.setTypeTechnicalTask(json.findPath("type").asInt());
        List<String> description = reader1.readValue(json.findPath("tasks"));
        task.setTaskTime(reader2.readValue(json.findPath("tasksTime")));

        List<Task> tasks = new ArrayList<>();
        description.stream().forEach(d -> {
            Task t = new Task();
            t.setDescription(d);
            tasks.add(t);
        });
        final int[] counter = {0};
        task.getTaskTime().stream().forEach(tt -> {
            tasks.get(counter[0]).setDate(tt);
            counter[0]++;
        });
        task.setTasks(tasks);
        List<List<String>> demandDescription = new ArrayList<>();
        List<String> demand = new ArrayList<>();
        List<List<String>> claims = new ArrayList<>();
        String str = json.findPath("claims").toString();
        List<List<String>> list = reader.readValue(json.findPath("claims"));
        for (List<String> elem : list) {
            demand.add(elem.get(0));
        }
        demandDescription = list.stream().peek(elem -> elem.remove(0)).collect(Collectors.toList());
        for (int i = 0; i < demand.size(); i++) {
            task.getDemands().put(demand.get(i), demandDescription.get(i));
        }
        technicalTaskRepository.updateTechnicalTask(task);
        return HttpStatus.CREATED;
    }
}



