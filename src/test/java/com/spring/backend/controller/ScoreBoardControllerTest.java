/*package com.spring.backend.controller;

import com.spring.backend.controller.ScoreBoardController;
import com.spring.backend.model.*;
import com.spring.backend.repository.ScoreboardRepository;
import com.spring.backend.service.ScoreBoardService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class ScoreBoardControllerTest {

    @Autowired
    private ScoreBoardController scoreBoardController;
    @Autowired
    private ScoreboardRepository scoreboardRepository;
    @Autowired
    private ScoreBoardService scoreBoardService;

    private Scoreboard scoreboard;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("testName","test@hotmail.com","test123");
        scoreboard = new Scoreboard(1L,10,4,0,user);
    }

    @AfterEach
    void tearDown() {
        user = null;
        scoreboard = null;
    }

    @Test
    void getAllScoreboard() {
        scoreboardRepository.save(scoreboard);
        List<Scoreboard> list = scoreBoardController.getAllScoreboard();
        assertThat(list).size().isGreaterThan(0);
    }

    @Test
    void addScoreboard() {
        scoreBoardService.addScoreBoard(scoreboard);
        assertNotNull(scoreboardRepository.findById(1L).get());
    }

    @Test
    @Transactional
    void getUserScoreboard() {
        scoreboardRepository.save(scoreboard);
        assertEquals(4,scoreBoardController.getUserScoreboard(1L).getWeeklyScore());
    }

    @Test
    @Transactional
    void modifyScoreboardDaily() {
        scoreboardRepository.save(scoreboard);
        Scoreboard result = scoreBoardService.modifyScoreBoardDaily(1L,2);
        assertEquals(12,result.getDailyScore());
    }

    @Test
    @Transactional
    void modifyScoreboardWeekly() {
        scoreboardRepository.save(scoreboard);
        Scoreboard result = scoreBoardService.modifyScoreBoardWeekly(1L,5);
        assertEquals(9,result.getWeeklyScore());
    }

    @Test
    @Transactional
    void modifyScoreboardMonthly() {
        scoreboardRepository.save(scoreboard);
        Scoreboard result = scoreBoardService.modifyScoreBoardMonthly(1L,3);
        assertEquals(3,result.getMonthlyScore());
    }

}
*/