package com.pwc.survivorcamp.Service;

import com.pwc.survivorcamp.model.Robot;
import com.pwc.survivorcamp.model.Survivors;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SurviveTheAplocalypseService {

    public String registerSurvivors(Survivors survivors);

    public List<Survivors> getSurvivors();

    public List<Survivors> getInfectedSurvivorsList();

    public List<Survivors> getNonInfectedSurvivorsList();

    public String reportInfected(int reporterId, int infectedId);

    public Map<String,Object> survivorCampReportGeneration();

    public String updateSurvivorLocation(int userId, double latitude, double longitude);

    public Map<String, Object> getEnemeyRobotDetais();
}
