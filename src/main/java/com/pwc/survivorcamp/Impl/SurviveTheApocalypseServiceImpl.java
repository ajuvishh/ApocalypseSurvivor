package com.pwc.survivorcamp.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwc.survivorcamp.Service.SurviveTheAplocalypseService;
import com.pwc.survivorcamp.dao.InfectedRepo;
import com.pwc.survivorcamp.dao.SurvivorsRepo;
import com.pwc.survivorcamp.model.ReportInfected;
import com.pwc.survivorcamp.model.ReportInfectedKey;
import com.pwc.survivorcamp.model.Robot;
import com.pwc.survivorcamp.model.Survivors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SurviveTheApocalypseServiceImpl implements SurviveTheAplocalypseService {

    @Autowired
    SurvivorsRepo survivorsRepo;

    @Autowired
    InfectedRepo infectedRepo;

    @Override
    public String registerSurvivors(Survivors survivors) {
        survivorsRepo.save(survivors);
        return "Data saved";
    }

    @Override
    public List<Survivors> getSurvivors() {
        return survivorsRepo.findAll();
    }

    @Override
    public List<Survivors> getInfectedSurvivorsList() {
        return survivorsRepo.findByInfected(true);
    }

    @Override
    public List<Survivors> getNonInfectedSurvivorsList() {
        return survivorsRepo.findByInfected(false);
    }

    @Override
    public String reportInfected(int reporterId, int infectedId) {

        Survivors reporter = survivorsRepo.findById(reporterId).get();
        Survivors infected = survivorsRepo.findById(infectedId).get();

        if(!infected.isInfected()) {
            ReportInfectedKey rk = new ReportInfectedKey(reporter, infected);

            ReportInfected infectedReported = new ReportInfected(rk);
            infectedRepo.save(infectedReported);

            if(infectedRepo.countByReportInfectedKey_infectedId(infected)>=3){
                infected.setInfected(true);
                survivorsRepo.save(infected);
            }
            return "Reported";
        }
        return "Already identified as INFECTED. Thank you for looking out!";
    }

    @Override
    public Map<String, Object> survivorCampReportGeneration() {
        Map<String,Object> report = new HashMap<>();
        report.put("Non-Infected",getNonInfectedSurvivorsList());
        report.put("Infected",getInfectedSurvivorsList());
        report.put("Infected Percentage in camp",9);
        report.put("Non-infected Percentage in camp",91);
        return report;
    }

    @Override
    public String updateSurvivorLocation(int userId, double latitude, double longitude) {
        Survivors survivors = survivorsRepo.findById(userId).get();
        if( survivors!=null ){
            survivors.setLatitude(latitude);
            survivors.setLongitude(longitude);
            survivorsRepo.save(survivors);
            return "Location updated";
        }
        return "Survivor with given id not present";
    }

    @Override
    public Map<String, Object> getEnemeyRobotDetais() {
        Map<String,Object> response = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            File robotList =  new ClassPathResource("roboList.json").getFile();
            List<Robot> robots = mapper.readValue(robotList,List.class);
            List<Robot> robotLists = mapper.convertValue(robots, new TypeReference<List<Robot>>() { });

            Map<String, Long> result = robotLists.stream().collect(Collectors.groupingBy(robo->robo.getCategory(),Collectors.counting()));
           response.put("identifiedEnemyRobotsByCategory", (Object) result);

            Map<String, Long> modelResult = robotLists.stream().collect(Collectors.groupingBy(robo->robo.getModel(),Collectors.counting()));
            response.put("identifiedEnemyRobotsByModel", (Object) modelResult);

            Map<String,List<Robot>> mappingResult = robotLists.stream().collect(Collectors.groupingBy(robo-> robo.getCategory()));
            response.put("categoryToRobotInfoMapping",mappingResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
