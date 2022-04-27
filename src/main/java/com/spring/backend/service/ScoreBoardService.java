package com.spring.backend.service;

import com.spring.backend.model.Scoreboard;
import com.spring.backend.repository.ScoreboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreBoardService {

    private ScoreboardRepository scoreboardRepository;


    @Autowired
    public ScoreBoardService(ScoreboardRepository scoreboardRepository) {
        this.scoreboardRepository = scoreboardRepository;
    }

    public Scoreboard addScoreBoard(Scoreboard scoreboard){
        return scoreboardRepository.save(scoreboard);
    }
}
