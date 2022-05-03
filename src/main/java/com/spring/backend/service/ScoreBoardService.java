package com.spring.backend.service;

import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Scoreboard;
import com.spring.backend.model.User;
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

    public Scoreboard modifyScoreBoardDaily(User user,int dailypoints) throws ResourceNotFoundException{
        if(scoreboardRepository.findById(user).isPresent()){
           Scoreboard temp = scoreboardRepository.getById(user);
           temp.setDailyScore(temp.getDailyScore()+dailypoints);
           return scoreboardRepository.save(temp);
        }
        throw new ResourceNotFoundException();
    }

    public Scoreboard modifyScoreBoardWeekly(User user,int weeklypoints) throws ResourceNotFoundException{
        if(scoreboardRepository.findById(user).isPresent()){
            Scoreboard temp = scoreboardRepository.getById(user);
            temp.setWeeklyScore(temp.getWeeklyScore()+weeklypoints);
            return scoreboardRepository.save(temp);
        }
        throw new ResourceNotFoundException();
    }

    public Scoreboard modifyScoreBoardMonthly(User user, int monthlyPoints) throws ResourceNotFoundException{
        if(scoreboardRepository.findById(user).isPresent()){
            Scoreboard temp = scoreboardRepository.getById(user);
            temp.setMonthlyScore(temp.getMonthlyScore()+monthlyPoints);
            return scoreboardRepository.save(temp);
        }
        throw new ResourceNotFoundException();
    }

    public Scoreboard getScoreForUser(User user) throws ResourceNotFoundException {
        if(scoreboardRepository.findById(user).isPresent()){
            return scoreboardRepository.getById(user);
        }
        throw new ResourceNotFoundException();
    }
}
