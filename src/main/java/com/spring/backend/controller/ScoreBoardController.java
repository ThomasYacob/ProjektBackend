package com.spring.backend.controller;

import com.spring.backend.model.Scoreboard;
import com.spring.backend.service.ScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("api/scoreboard")

public class ScoreBoardController {
    @Autowired
    private ScoreBoardService scoreBoardService;

    @PutMapping("/monthly/{userid}")
    public Scoreboard adjustMonthlyScoreboard(@PathVariable String email,@RequestBody int monthlyPoints){
        return scoreBoardService.modifyScoreBoardMonthly(email,monthlyPoints);
    }
    @PutMapping("/weekly/{userid}")
    public Scoreboard adjustweeklyScoreboard(@PathVariable String email,@RequestBody int weeklyPoints){
        return scoreBoardService.modifyScoreBoardWeekly(email,weeklyPoints);
    }

}
