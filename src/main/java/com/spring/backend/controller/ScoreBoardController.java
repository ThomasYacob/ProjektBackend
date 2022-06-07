package com.spring.backend.controller;

import com.spring.backend.model.Scoreboard;
import com.spring.backend.service.ScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The controller class of the "Scoreboard" REST API methods. This class is responsible
 * for processing incoming HTTP requests, preparing a model and returning
 * a response.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("api/scoreboard")
public class ScoreBoardController {
    @Autowired
    private ScoreBoardService scoreBoardService;

    /**
     * REST API Method to get all Scoreboards.
     */
    @GetMapping("all")
    public List<Scoreboard> getAllScoreboard(){
        return this.scoreBoardService.getAllScoreBoard();
    }

    @PutMapping("alterScoreBoard/{userId}/{type}")
    public Scoreboard alterScoreBoard(@PathVariable String username, @PathVariable Long type,@RequestBody int points){
        return this.scoreBoardService.alterScoreBoard(username,type,points);
    }

    /**
     * REST API Method to get a Scoreboard by User ID.
     * @param id the ID of the User.
     */
    @GetMapping("{id}")
    public Scoreboard getUserScoreboard(@PathVariable Long id){
        return this.scoreBoardService.getScoreForUser(id);
    }
}
