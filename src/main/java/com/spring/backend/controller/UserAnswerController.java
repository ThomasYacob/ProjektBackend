package com.spring.backend.controller;


import com.spring.backend.model.UserAnswer;
import com.spring.backend.repository.UserAnswerRepository;
import com.spring.backend.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("api/UserAnswer")

public class UserAnswerController {

    @Autowired
    private UserAnswerService userAnswerService;

    @ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
    @PostMapping
    public UserAnswer  createNewUserAnswer(@RequestBody UserAnswer userAnswer){
        try{
            return userAnswerService.createNewUserAnswer(userAnswer);
        }
        catch (RuntimeException e){
            throw new RuntimeException("Answer already submitted");
        }
    }


}