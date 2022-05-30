/*package com.spring.backend.controller;

import com.spring.backend.controller.DailyController;
import com.spring.backend.model.Daily;
import com.spring.backend.repository.DailyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith (SpringRunner.class)
@ActiveProfiles("test")
class DailyControllerTest {

    @Autowired
    private DailyController dailyController;
    @Autowired
    private DailyRepository dailyRepository;

    private Daily daily;

   @BeforeEach
   void setUp() {
       daily = new Daily(1L,"Vart ligger Stockholm","I Sverige","I Norge","I Finland",2,new Date(1990L));
   }

   @AfterEach
   void tearDown() {
       daily = null;
   }

    @Test
    void getDaily() {
        dailyRepository.save(daily);
        List<Daily> list = dailyController.getDaily();
        assertThat(list).size().isGreaterThan(0);
    }

    @Test
    void getDailyById() {
       dailyRepository.save(daily);
       Daily test = dailyController.getDailyById(1L);
       assertNotNull(test);
    }

    @Test
    void createNewDaily() {
        dailyController.createNewDaily(daily);
        assertNotNull(dailyRepository.findById(1L).get());
    }

    @Test
    void DeleteDaily() {
        dailyRepository.save(daily);
        dailyController.deleteDaily(1L);
        assertThat(dailyRepository.findById(1L).isEmpty());
    }
}

*/
