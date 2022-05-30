package com.spring.backend.controller;

import com.spring.backend.model.Scoreboard;
import com.spring.backend.service.ScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
    @PutMapping("alterScoreBoard/{userId}/{type}")
    public Scoreboard alterScoreBoard(@PathVariable String username, @PathVariable Long type,@RequestBody int points){
        return this.scoreBoardService.alterScoreBoard(username,type,points);
    }

    @GetMapping("{id}")
    public Scoreboard getUserScoreboard(@PathVariable Long id){
        return this.scoreBoardService.getScoreForUser(id);
    }

}
