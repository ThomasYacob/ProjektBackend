package com.spring.backend.controller;

import com.spring.backend.model.Scoreboard;
import com.spring.backend.service.ScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("api/scoreboard")

public class ScoreBoardController {
    @Autowired
    private ScoreBoardService scoreBoardService;

    @GetMapping("all")
    public List<Scoreboard> getAllScoreboard(){
        return this.scoreBoardService.getAllScoreBoard();
    }

    @GetMapping("{userid}")
    public Scoreboard getUserScoreboard(@PathVariable String userid){
        return scoreBoardService.getScoreForUser(userid);
    }

}
