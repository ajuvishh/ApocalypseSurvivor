package com.pwc.survivorcamp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwc.survivorcamp.Service.SurviveTheAplocalypseService;
import com.pwc.survivorcamp.model.Robot;
import com.pwc.survivorcamp.model.Survivors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SurvivorsController {

    @Autowired
    SurviveTheAplocalypseService surviveTheAplocalypseService;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    ObjectMapper mapper;

    @GetMapping("/getSurvivors")
    public ResponseEntity<List<Survivors>> getSurvivors(){
        return ResponseEntity.accepted().body(surviveTheAplocalypseService.getSurvivors());
    }

    @PostMapping("/registerSurvivors")
    public ResponseEntity<String> addSurvivor(@Valid @RequestBody Survivors survivors){
        return ResponseEntity.ok().body(surviveTheAplocalypseService.registerSurvivors(survivors));
    }

    @PostMapping("/updateSurvivorLocation/{userId}")
    public ResponseEntity<String> updateSurvivorLocation(@PathVariable int userId,@RequestParam double latitude, @RequestParam double longitude){
        return ResponseEntity.ok().body(surviveTheAplocalypseService.updateSurvivorLocation(userId, latitude, longitude));
    }

    @PostMapping("/reportInfected")
    public ResponseEntity<String> reportInfectedSurvivor(@RequestParam int reporterId,@RequestParam int infectedId){
        return ResponseEntity.ok().body(surviveTheAplocalypseService.reportInfected(reporterId,infectedId));
    }

    @GetMapping("/getReports")
    public ResponseEntity<Map<String,Object>> getReportDetails(){
        Map<String,Object> response = new HashMap<>();
        
        response = surviveTheAplocalypseService.survivorCampReportGeneration();

        try {
            File robotList =  new ClassPathResource("roboList.json").getFile();
            List<Robot> robots = mapper.readValue(robotList,List.class);
            response.put("Robot List",robots);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.accepted().body(response);
    }

    @GetMapping("/getEnemyRobotInfo")
    public Map<String,Object> getEnemyRobotDetails(){
        return surviveTheAplocalypseService.getEnemeyRobotDetais();
    }
}
