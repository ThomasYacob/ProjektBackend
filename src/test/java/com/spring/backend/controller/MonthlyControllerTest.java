package com.spring.backend.controller;

import com.spring.backend.model.Monthly;
import com.spring.backend.repository.MonthlyRepository;
import com.spring.backend.service.MonthlyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class MonthlyControllerTest {

    @Autowired
    private MonthlyController monthlyController;
    @Autowired
    private MonthlyService monthlyService;
    @Autowired
    private MonthlyRepository monthlyRepository;

    private Monthly monthly;

    @BeforeEach
    void setUp() {
        monthly = new Monthly(1L,"Create a new MVC-application","The answer","Model","View","Controller",new Date(1990L));
    }

    @AfterEach
    void tearDown() {
        monthly = null;
    }

    @Test
    void getAllMonthly() {
        monthlyRepository.save(monthly);
        List<Monthly> list = monthlyController.getAllMonthly();
        assertThat(list).size().isGreaterThan(0);
    }

    @Test
    void getMonthlyById() {
        monthlyRepository.save(monthly);
        Monthly test = monthlyController.getMonthlyById(1L);
        assertNotNull(test);
    }

    @Test
    @Transactional
    void getAnswerById() {
        monthlyRepository.save(monthly);
        assertEquals("The answer",monthlyService.findAnswerById(1L));
    }

    @Test
    void createNewMonthly() {
        monthlyController.createNewMonthly(monthly);
        assertNotNull(monthlyRepository.findById(1L).get());
    }

    @Test
    void deleteMonthly() {
        monthlyRepository.save(monthly);
        monthlyController.deleteMonthly(1L);
        assertThat(monthlyRepository.findById(1L).isEmpty());
    }
}
