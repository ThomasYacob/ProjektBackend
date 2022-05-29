package com.spring.backend.service;

import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.User;
import com.spring.backend.model.UserAnswer;
import com.spring.backend.model.Weekly;
import com.spring.backend.model.typeOfQuestion;
import com.spring.backend.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * This service layer holds the business logic for the UserAnswerController class.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Service
public class UserAnswerService {

    private final UserAnswerRepository userAnswerRepository;
    private final DailyService dailyService;
    private final ScoreBoardService scoreBoardService;
    private final MonthlyService monthlyService;
    private final WeeklyService weeklyService;
    private final UserService userService;

    /**
     * Creates the necessary repositories for this service layer,
     * in order to perform CRUD operations on the database.
     * @param scoreBoardService the Service layer for Scoreboard.
     * @param dailyService the Service layer for Daily.
     * @param monthlyService the Service layer for Monthly.
     * @param userService the Service layer for User.
     * @param weeklyService the Service layer for Weekly.
     * @param userAnswerRepository the Repository for the UserAnswer Objects.
     */
    @Autowired
    public UserAnswerService(UserAnswerRepository userAnswerRepository, DailyService dailyService, ScoreBoardService scoreBoardService, MonthlyService monthlyService, WeeklyService weeklyService, UserService userService) {
        this.userAnswerRepository = userAnswerRepository;
        this.dailyService = dailyService;
        this.scoreBoardService = scoreBoardService;
        this.monthlyService = monthlyService;
        this.weeklyService = weeklyService;
        this.userService = userService;
    }

    /**
     * Get the answers by a specific User from the database, based on ID.
     * @param id the ID of the User.
     */
    public Set<UserAnswer> getUserAnswers(Long id){
        User user = userAnswerRepository.findUser(id);
        if (user != null){
            if(!userAnswerRepository.findUserAnswerByUsername(id).isEmpty()){
                return userAnswerRepository.findUserAnswerByUsername(id);
            }
            else throw new ResourceNotFoundException("Answers not found");
        }
        else throw new ResourceNotFoundException("User not found");

    }

    /**
     * Get the all the users' answers from the database.
     */
    public List<UserAnswer> getAll(){
        return this.userAnswerRepository.findAll();
    }

    /**
     * Saves a User's answer in the database, based on ID.
     * @param id the ID of the User.
     * @param userAnswer the User's answer to save.
     */
    public UserAnswer setUserAnswer(Long id, UserAnswer userAnswer){
        User user = userAnswerRepository.findUser(id);
        userAnswer.setUser(user);
        if(userAnswer.getTypeOfQuestion()== typeOfQuestion.Daily){
            if(dailyService.getDaily(userAnswer.getQuestionId()).getRightAlternative() == Integer.parseInt(userAnswer.getAnswer())){
                scoreBoardService.modifyScoreBoardDaily(id,1);
            }
        }
        long millis = System.currentTimeMillis();
        userAnswer.setDate(new Date(millis));
        return userAnswerRepository.save(userAnswer);
    }
}
