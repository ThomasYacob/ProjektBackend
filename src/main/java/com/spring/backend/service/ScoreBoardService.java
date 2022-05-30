package com.spring.backend.service;

import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Scoreboard;
import com.spring.backend.model.User;
import com.spring.backend.repository.ScoreboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreBoardService {

    private final ScoreboardRepository scoreboardRepository;

    @Autowired
    public ScoreBoardService(ScoreboardRepository scoreboardRepository) {
        this.scoreboardRepository = scoreboardRepository;
    }

    public Scoreboard addScoreBoard(Scoreboard scoreboard){
        return scoreboardRepository.save(scoreboard);
    }

    public Scoreboard modifyScoreBoardDaily(Long id,int dailyPoints) throws ResourceNotFoundException {
        User user = scoreboardRepository.findByIdUser(id);
        if(user != null){
            Scoreboard temp = scoreboardRepository.findScoreboardByUser(user);
            temp.setDailyScore(temp.getDailyScore()+dailyPoints);
            return scoreboardRepository.save(temp);
        }
        throw new ResourceNotFoundException();
    }

    public Scoreboard modifyScoreBoardWeekly(String id, int weeklyPoints) throws ResourceNotFoundException{
        User user = scoreboardRepository.findByUsername(id);
        if(user != null){
            Scoreboard temp = scoreboardRepository.findScoreboardByUser(user);
            temp.setWeeklyScore(temp.getWeeklyScore()+weeklyPoints);
            return scoreboardRepository.save(temp);
        }
        throw new ResourceNotFoundException();
    }

    public Scoreboard modifyScoreBoardMonthly(String id, int monthlyPoints) throws ResourceNotFoundException{
        User user = scoreboardRepository.findByUsername(id);
        if(user != null){
            Scoreboard temp = scoreboardRepository.findScoreboardByUser(user);
            temp.setMonthlyScore(temp.getMonthlyScore()+monthlyPoints);
            return scoreboardRepository.save(temp);
        }
        throw new ResourceNotFoundException();
    }

    public Scoreboard alterScoreBoard(String username,Long type,int points){

        if(type == 1){
            return modifyScoreBoardMonthly(username,points);
        }else if(type == 2){
           return modifyScoreBoardWeekly(username,points);
        }
        else return null;
    }


    public List<Scoreboard> getAllScoreBoard(){
        return scoreboardRepository.findAll();
    }


    public Scoreboard getScoreForUser(Long id) throws ResourceNotFoundException {
        User user = scoreboardRepository.findByIdUser(id);
        if(user != null){
            return scoreboardRepository.findScoreboardByUser(user);
        }
        throw new ResourceNotFoundException();
    }
}
