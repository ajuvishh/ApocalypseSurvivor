package com.pwc.survivorcamp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwc.survivorcamp.Service.SurviveTheAplocalypseService;
import com.pwc.survivorcamp.dao.InfectedRepo;
import com.pwc.survivorcamp.dao.SurvivorsRepo;
import com.pwc.survivorcamp.model.Survivors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
class SurvivorsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JdbcTemplate h2;

    @Mock
    SurvivorsRepo survivorsRepo;

    @Mock
    InfectedRepo infectedRepo;

    @Mock
    SurviveTheAplocalypseService surviveTheAplocalypseService;

    @InjectMocks
    SurvivorsController survivorsController;

    @Mock
    Survivors survivor;

    @BeforeEach
    public void beforeEach(){
        survivor.setId(1);
        survivor.setAge(23);
        survivor.setGender("Female");
        survivor.setName("Disha");
    }

    @Test
    void getSurvivors() throws Exception {
        when(surviveTheAplocalypseService.getSurvivors()).thenReturn(Arrays.asList(survivor));
        mockMvc.perform(MockMvcRequestBuilders.get("/getSurvivors").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    void addSurvivor() throws Exception {
        when(surviveTheAplocalypseService.registerSurvivors(survivor)).thenReturn("Data saved");
        ObjectMapper objectMapper= new ObjectMapper();
        Map<String,Object> body = new HashMap<>();
        mockMvc.perform(MockMvcRequestBuilders.post("/registerSurvivors").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(body)))
                 .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void reportInfected() throws Exception {
        when(surviveTheAplocalypseService.reportInfected(1,2)).thenReturn("Reported");
        mockMvc.perform(MockMvcRequestBuilders.post("/reportInfected").param("reporterId","1").param("infectedId","2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getReportDetails() throws Exception {
        Map<String,Object> response = new HashMap<>();
        response.put("Non-Infected",survivor);
        response.put("Infected",null);
        response.put("Infected Percentage in camp",9);
        response.put("Non-infected Percentage in camp",91);

        when(surviveTheAplocalypseService.survivorCampReportGeneration()).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.get("/getReports").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

}