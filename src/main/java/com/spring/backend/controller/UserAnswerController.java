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

/**
 * The controller class of the "User's Answer" REST API methods. This class is responsible
 * for processing incoming HTTP requests, preparing a model and returning
 * a response.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/api")

public class UserAnswerController {

    @Autowired
    private UserAnswerService userAnswerService;
    @Autowired
    private UserService userService;

    /**
     * REST API Method to get a User Answer by User ID.
     * @param id the ID of the User.
     */
    @GetMapping("/user/{id}/userAnswers")
    public Set<UserAnswer> getAnswers(@PathVariable(value = "id") Long id){
        return userAnswerService.getUserAnswers(id);
    }

    /**
     * REST API Method to get all User Answers.
     */
    @GetMapping("/user/userAnswers")
    public List<UserAnswer> getAll(){
        return userAnswerService.getAll();
    }

    /**
     * REST API Method to create a new User Answer.
     * @param id the ID of the User.
     * @param userAnswer the User Answer to create.
     */
    @PostMapping("/user/{id}/userAnswers")
    public UserAnswer setAnswer(@PathVariable(value = "id") Long id,@RequestBody UserAnswer userAnswer){
        return userAnswerService.setUserAnswer(id,userAnswer);
    }

    /*@GetMapping("/userAnswers/uncorrected")
    public List<UserAnswer> getUncorrected(){

    }*/
}
