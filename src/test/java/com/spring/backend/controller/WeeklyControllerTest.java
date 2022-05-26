package com.spring.backend.controller;

import com.spring.backend.model.Weekly;
import com.spring.backend.repository.WeeklyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class WeeklyControllerTest {

    @Autowired
    private WeeklyController weeklyController;
    @Autowired
    private WeeklyRepository weeklyRepository;

    private Weekly weekly;

    @BeforeEach
    void setUp() {
        weekly = new Weekly(1L,"What is JWT?","Token",new Date(1990L));
    }

    @AfterEach
    void tearDown() {
        weekly = null;
    }

    @Test
    void getAllWeekly() {
        weeklyRepository.save(weekly);
        List<Weekly> list = weeklyController.getAllWeekly();
        assertThat(list).size().isGreaterThan(0);
    }

    @Test
    void getWeeklyById() {
        weeklyRepository.save(weekly);
        Weekly test = weeklyController.getWeeklyById(1L);
        assertNotNull(test);
    }

    @Test
    void createNewWeekly() {
        weeklyController.createNewWeekly(weekly);
        assertNotNull(weeklyRepository.findById(1L).get());
    }

    @Test
    void deleteWeekly() {
        weeklyRepository.save(weekly);
        weeklyController.deleteWeekly(1L);
        assertThat(weeklyRepository.findById(1L).isEmpty());
    }
}
