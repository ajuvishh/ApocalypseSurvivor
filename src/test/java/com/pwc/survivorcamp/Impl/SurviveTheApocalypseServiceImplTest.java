package com.pwc.survivorcamp.Impl;

import com.pwc.survivorcamp.dao.InfectedRepo;
import com.pwc.survivorcamp.dao.SurvivorsRepo;
import com.pwc.survivorcamp.model.Survivors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SurviveTheApocalypseServiceImplTest {
    @Mock
    SurvivorsRepo survivorsRepo;

    @Mock
    InfectedRepo infectedRepo;

    @InjectMocks
    SurviveTheApocalypseServiceImpl surviveTheApocalypseService;

    Survivors survivor;

    @BeforeEach
    public void beforeEach(){
        survivor = new Survivors();
        survivor.setId(1);
        survivor.setAge(23);
        survivor.setGender("Female");
        survivor.setName("Disha");
    }

    @Test
    void assertgetSurvivors(){
        when(survivorsRepo.findAll()).thenReturn(Arrays.asList(survivor));
        assertEquals(surviveTheApocalypseService.getSurvivors(),Arrays.asList(survivor));
        verify(survivorsRepo,times(1)).findAll();
    }

    @Test
    void assertRegisterSurvivors(){
        when(survivorsRepo.save(survivor)).thenReturn(survivor);
        assertEquals(surviveTheApocalypseService.registerSurvivors(survivor),"Data saved");
        verify(survivorsRepo,times(1)).save(survivor);
    }

    @Test
    void assertGetInfectedSurvivorsList(){
        when(survivorsRepo.findByInfected(true)).thenReturn(Arrays.asList(survivor));
        assertEquals(surviveTheApocalypseService.getInfectedSurvivorsList(),Arrays.asList(survivor));
        verify(survivorsRepo,times(1)).findByInfected(true);
    }

    @Test
    void assertGetNonInfectedSurvivorsList(){
        when(survivorsRepo.findByInfected(false)).thenReturn(Arrays.asList(survivor));
        assertEquals(surviveTheApocalypseService.getNonInfectedSurvivorsList(),Arrays.asList(survivor));
        verify(survivorsRepo,times(1)).findByInfected(false);
    }

    @Test
    void assertReportInfected(){
        Survivors infected = new Survivors(2,25,"John","Male",false,11.07,54.32);
        when(survivorsRepo.findById(1)).thenReturn(java.util.Optional.ofNullable(survivor));
        when(survivorsRepo.findById(2)).thenReturn(java.util.Optional.of(infected));
        assertEquals(surviveTheApocalypseService.reportInfected(1,2),"Reported");
        verify(survivorsRepo,times(1)).findById(1);
        verify(survivorsRepo,times(1)).findById(2);
    }
}