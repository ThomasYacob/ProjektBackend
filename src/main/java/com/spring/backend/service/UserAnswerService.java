package com.spring.backend.service;


import com.spring.backend.model.UserAnswer;
import com.spring.backend.model.UserAnswerId;
import com.spring.backend.model.Weekly;
import com.spring.backend.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class UserAnswerService {

    private final UserAnswerRepository userAnswerRepository;
    private final DailyService dailyService;
    private final ScoreBoardService scoreBoardService;
    private final MonthlyService monthlyService;
    private final WeeklyService weeklyService;

    @Autowired
    public UserAnswerService(UserAnswerRepository userAnswerRepository, DailyService dailyService, ScoreBoardService scoreBoardService, MonthlyService monthlyService, WeeklyService weeklyService) {
        this.userAnswerRepository = userAnswerRepository;
        this.dailyService = dailyService;
        this.scoreBoardService = scoreBoardService;
        this.monthlyService = monthlyService;
        this.weeklyService = weeklyService;
    }



    public UserAnswer createNewUserAnswer(UserAnswer userAnswer) {
        if(userAnswerRepository.findById(new UserAnswerId(userAnswer.getUser(),userAnswer.getScoreboard(),userAnswer.getDaily(),userAnswer.getMonthly(),userAnswer.getWeekly())).isPresent()){
            throw new RuntimeException();
        }
        switch(userAnswer.getType()){
            case "Daily" : if (Integer.parseInt(userAnswer.getAnswer())== dailyService.findAnswerById(userAnswer.getDaily().getId())) {
                scoreBoardService.modifyScoreBoardDaily(userAnswer.getUser(),1);
            }
            case "Weekly": if(userAnswer.getAnswer().equals(weeklyService.findAnswerById(userAnswer.getWeekly().getId()))){
                scoreBoardService.modifyScoreBoardWeekly(userAnswer.getUser(),1);
            }
            case "Monthly": if(userAnswer.getAnswer().equals(monthlyService.findAnswerById(userAnswer.getMonthly().getId()))){
                scoreBoardService.modifyScoreBoardMonthly(userAnswer.getUser(),1);
            }
        }
        return userAnswerRepository.save(userAnswer);
    }


}
