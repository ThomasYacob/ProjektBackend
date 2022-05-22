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
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/api")

public class UserAnswerController {

    @Autowired
    private UserAnswerService userAnswerService;
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}/userAnswers")
    public Set<UserAnswer> getAnswers(@PathVariable(value = "id") Long id){
        return userAnswerService.getUserAnswers(id);
    }

    @GetMapping("/user/userAnswers")
    public List<UserAnswer> getAll(){
        return userAnswerService.getAll();
    }

    @PostMapping("/user/{id}/userAnswers")
    public UserAnswer setAnswer(@PathVariable(value = "id") Long id,@RequestBody UserAnswer userAnswer){
        return userAnswerService.setUserAnswer(id,userAnswer);
    }


}
