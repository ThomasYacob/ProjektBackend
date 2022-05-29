package com.spring.backend.service;

import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Scoreboard;
import com.spring.backend.model.User;
import com.spring.backend.repository.ScoreboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * This service layer holds the business logic for the ScoreBoardController class.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Service
public class ScoreBoardService {

    private final ScoreboardRepository scoreboardRepository;

    /**
     * Creates a ScoreboardRepository for this service layer,
     * in order to perform CRUD operations on the database.
     * @param scoreboardRepository the Repository for the Scoreboard Objects.
     */
    @Autowired
    public ScoreBoardService(ScoreboardRepository scoreboardRepository) {
        this.scoreboardRepository = scoreboardRepository;
    }

    /**
     * Saves the Scoreboard in the database.
     * @param scoreboard the Scoreboard.
     */
    public Scoreboard addScoreBoard(Scoreboard scoreboard){
        return scoreboardRepository.save(scoreboard);
    }

    /**
     * Updates the Daily score on a specific User's Scoreboard in the database, based on ID.
     * @param id the ID of the User.
     * @param dailyPoints amount of points to add.
     * @throws ResourceNotFoundException
     */
    public Scoreboard modifyScoreBoardDaily(Long id,int dailyPoints) throws ResourceNotFoundException {
        User user = scoreboardRepository.findByIdUser(id);
        if(user != null){
            Scoreboard temp = scoreboardRepository.findScoreboardByUser(user);
            temp.setDailyScore(temp.getDailyScore()+dailyPoints);
            return scoreboardRepository.save(temp);
        }
        throw new ResourceNotFoundException();
    }

    /**
     * Updates the Weekly score on a specific User's Scoreboard in the database, based on ID.
     * @param id the ID of the User.
     * @param weeklyPoints amount of points to add.
     * @throws ResourceNotFoundException
     */
    public Scoreboard modifyScoreBoardWeekly(Long id, int weeklyPoints) throws ResourceNotFoundException{
        User user = scoreboardRepository.findByIdUser(id);
        if(user != null){
            Scoreboard temp = scoreboardRepository.findScoreboardByUser(user);
            temp.setWeeklyScore(temp.getWeeklyScore()+weeklyPoints);
            return scoreboardRepository.save(temp);
        }
        throw new ResourceNotFoundException();
    }

    /**
     * Updates the Monthly score on a specific User's Scoreboard in the database, based on ID.
     * @param id the ID of the User.
     * @param monthlyPoints amount of points to add.
     * @throws ResourceNotFoundException
     */
    public Scoreboard modifyScoreBoardMonthly(Long id, int monthlyPoints) throws ResourceNotFoundException{
        User user = scoreboardRepository.findByIdUser(id);
        if(user != null){
            Scoreboard temp = scoreboardRepository.findScoreboardByUser(user);
            temp.setMonthlyScore(temp.getMonthlyScore()+monthlyPoints);
            return scoreboardRepository.save(temp);
        }
        throw new ResourceNotFoundException();
    }

    /**
     * Get all the Scoreboards from the database.
     */
    public List<Scoreboard> getAllScoreBoard(){
        return this.scoreboardRepository.findAll();
    }

    /**
     * Get the score from the database for a specific User.
     * @param id the ID of the User.
     * @throws ResourceNotFoundException
     */
    public Scoreboard getScoreForUser(Long id) throws ResourceNotFoundException {
        User user = scoreboardRepository.findByIdUser(id);
        if(user != null){
            return scoreboardRepository.findScoreboardByUser(user);
        }
        throw new ResourceNotFoundException();
    }
}
