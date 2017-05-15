package com.romanov.controllers.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.romanov.model.Hardware;
import com.romanov.model.Software;
import com.romanov.model.TechnicalTask;
import com.romanov.repository.HardwareRepository;
import com.romanov.repository.SoftwareRepository;
import com.romanov.repository.TechnicalTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by Kirill_Romanov1 on 28-Mar-17.
 */

@RestController
@Scope("session")
public class TechnicalTaskRestController {

    @Autowired
    private TechnicalTaskRepository technicalTaskRepository;


    @RequestMapping(value = "/rest/technical-task", method = RequestMethod.POST)
    public HttpStatus addTecgnicalTask(@RequestBody JsonNode json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader reader = objectMapper.readerFor(new TypeReference<List<List<String>>>() {
        });
        //ObjectReader reader = objectMapper.reader();
        System.out.println(json.toString());

        TechnicalTask task = new TechnicalTask();

        task.setName(json.findPath("name").asText());
        task.setDateCreated(new Date());
        task.setTarget(json.findPath("target").asText());
        task.setDiscipline(json.findPath("discipline").asInt());
        task.setTypeTechnicalTask(json.findPath("type").asInt());


        List<String> demandDescription = new ArrayList<>();
        List<String> demand = new ArrayList<>();
        List<List<String>> claims = new ArrayList<>();
        String str=json.findPath("claims").toString();
        List<List<String>> list = reader.readValue(json.findPath("claims"));
        for (int i = 0; i < demand.size(); i++) {
            task.getDemands().put(demand.get(i), demandDescription.get(i));
        }
        System.out.println(task);
        //technicalTaskRepository.addTechnicalTask(task);
        return HttpStatus.CREATED;

    }


}



