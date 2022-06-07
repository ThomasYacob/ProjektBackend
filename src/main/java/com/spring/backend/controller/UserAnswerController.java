package com.spring.backend.controller;

import com.spring.backend.model.Daily;
import com.spring.backend.model.UserAnswer;
import com.spring.backend.service.UserAnswerService;
import com.spring.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
@Tag(name = "userAnswer", description = "Operations about Users' answers.")
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/api")

public class UserAnswerController {

    @Autowired
    private UserAnswerService userAnswerService;
    @Autowired
    private UserService userService;

    @Operation(summary = "Get Answer by ID", description = "Retrieves all Answers for a specific User, based on User ID",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("/user/{id}/userAnswers")
    public Set<UserAnswer> getAnswers(@PathVariable(value = "id") Long id){
        return userAnswerService.getUserAnswers(id);
    }

    @Operation(summary = "Get Answers", description = "Retrieves all Answers saved in the database.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("/user/userAnswers")
    public List<UserAnswer> getAll(){
        return userAnswerService.getAll();
    }

    @Operation(summary = "Create Answer", description = "Creates and saves a User's answer in the database.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @PostMapping("/user/{id}/userAnswers")
    public UserAnswer setAnswer(@PathVariable(value = "id") Long id,@RequestBody UserAnswer userAnswer){
        return userAnswerService.setUserAnswer(id,userAnswer);
    }

    /*@GetMapping("/userAnswers/uncorrected")
    public List<UserAnswer> getUncorrected(){

    }*/
}
