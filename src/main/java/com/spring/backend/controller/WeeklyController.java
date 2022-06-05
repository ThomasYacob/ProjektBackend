package com.spring.backend.controller;

import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Daily;
import com.spring.backend.model.Weekly;
import com.spring.backend.service.WeeklyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The controller class of the "Weekly" REST API methods. This class is responsible
 * for processing incoming HTTP requests, preparing a model and returning
 * a response.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Tag(name = "weekly", description = "Operations about Weekly Challenges.")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/weekly")
public class WeeklyController {

    @Autowired
    private WeeklyService weeklyService;

    @Operation(summary = "Get Weekly Challenges", description = "Retrieves all Weekly Challenges saved in the database.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("all")
    public List<Weekly> getAllWeekly(){
        return this.weeklyService.getAllWeekly();
    }

    @Operation(summary = "Get Weekly Challenge", description = "Retrieves the Weekly Challenge of the current week.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("getWeekly")
    public Weekly getTodayWeekly(){
        return this.weeklyService.todaysWeekly();
    }

    @Operation(summary = "Get Weekly Challenge by ID", description = "Retrieves a specific Weekly Challenge, based on ID.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("{id}")
    Weekly getWeeklyById(@PathVariable Long id){
        try {
            return this.weeklyService.getWeekly(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("weekly not found");
        }
    }

    @Operation(summary = "Create a new Weekly Challenge", description = "Creates and saves a Weekly Challenge in the database.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @PostMapping()
    Weekly createNewWeekly(@RequestBody Weekly newWeekly){
        return this.weeklyService.createNewWeekly(newWeekly);
    }

    @Operation(summary = "Delete a Weekly Challenge", description = "Deletes a Weekly Challenge from the database.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @DeleteMapping("{id}")
    void deleteWeekly(@PathVariable Long id){
        try{
            this.weeklyService.deleteWeekly(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("weekly not found");
        }
    }
}
