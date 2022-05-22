package com.spring.backend.controller;

import com.spring.backend.model.Scoreboard;
import com.spring.backend.service.ScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("api/scoreboard")

public class ScoreBoardController {
    @Autowired
    private ScoreBoardService scoreBoardService;

    @GetMapping("all")
    public List<Scoreboard> getAllScoreboard(){
        return this.scoreBoardService.getAllScoreBoard();
    }

    @GetMapping("{id}")
    public Scoreboard getUserScoreboard(@PathVariable Long id){
        return scoreBoardService.getScoreForUser(id);
    }

}
