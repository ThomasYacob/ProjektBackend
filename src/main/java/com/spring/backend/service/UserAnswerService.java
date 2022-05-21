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

import java.util.List;
import java.util.Set;

@Service
public class UserAnswerService {

    private final UserAnswerRepository userAnswerRepository;
    private final DailyService dailyService;
    private final ScoreBoardService scoreBoardService;
    private final MonthlyService monthlyService;
    private final WeeklyService weeklyService;
    private final UserService userService;

    @Autowired
    public UserAnswerService(UserAnswerRepository userAnswerRepository, DailyService dailyService, ScoreBoardService scoreBoardService, MonthlyService monthlyService, WeeklyService weeklyService, UserService userService) {
        this.userAnswerRepository = userAnswerRepository;
        this.dailyService = dailyService;
        this.scoreBoardService = scoreBoardService;
        this.monthlyService = monthlyService;
        this.weeklyService = weeklyService;
        this.userService = userService;
    }

    public Set<UserAnswer> getUserAnswers(String email){
        User user = userAnswerRepository.findUser(email);
        if (user != null){
            if(!userAnswerRepository.findUserAnswerByUsername(email).isEmpty()){
                return userAnswerRepository.findUserAnswerByUsername(email);
            }
            else throw new ResourceNotFoundException("Answers not found");
        }
        else throw new ResourceNotFoundException("User not found");

    }

    public List<UserAnswer> getAll(){
        return this.userAnswerRepository.findAll();
    }


    public UserAnswer setUserAnswer(String email,UserAnswer userAnswer){
        User user = userAnswerRepository.findUser(email);
        userAnswer.setUser(user);
        if(userAnswer.getTypeOfQuestion()== typeOfQuestion.Daily){
            if(dailyService.getDaily(userAnswer.getQuestionId()).getRightAlternative() == Integer.parseInt(userAnswer.getAnswer())){
                scoreBoardService.modifyScoreBoardDaily(email,1);
            }
        }
        return userAnswerRepository.save(userAnswer);
    }





}
