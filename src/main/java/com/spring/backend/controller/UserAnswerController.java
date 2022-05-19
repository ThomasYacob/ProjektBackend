package com.spring.backend.controller;


import com.spring.backend.model.UserAnswer;
import com.spring.backend.repository.UserAnswerRepository;
import com.spring.backend.service.UserAnswerService;
import com.spring.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("api")

public class UserAnswerController {

    @Autowired
    private UserAnswerService userAnswerService;
    @Autowired
    private UserService userService;

    @GetMapping("/user/{userid}/userAnswers")
    public Set<UserAnswer> getAnswers(@PathVariable(value = "userid") String email){
        return userAnswerService.getUserAnswers(email);
    }

    @PostMapping("/user/{userid}/userAnswers")
    public UserAnswer setAnswer(@PathVariable(value = "userid") String email,@RequestBody UserAnswer userAnswer){
        return userAnswerService.setUserAnswer(email,userAnswer);
    }


}
