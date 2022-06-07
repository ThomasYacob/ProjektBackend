package com.spring.backend.controller;

import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Daily;
import com.spring.backend.service.DailyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The controller class of the "Daily" REST API methods. This class is responsible
 * for processing incoming HTTP requests, preparing a model and returning
 * a response.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Tag(name = "daily", description = "Operations about Daily Challenges.")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/daily")
public class DailyController {

    @Autowired
    private DailyService dailyService;

    @Operation(summary = "Get Daily Challenges", description = "Retrieves all Daily Challenges saved in the database.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("all")
    public List<Daily> getDaily(){
        return this.dailyService.getAllDaily();
    }

    @Operation(summary = "Get Daily Challenge by ID", description = "Retrieves a specific Daily Challenge, based on ID.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("{id}")
    public Daily getDailyById(@PathVariable Long id){
        try {
            return this.dailyService.getDaily(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Daily not found");
        }
    }

    @Operation(summary = "Get Today's Daily Challenge", description = "Retrieves the Daily Challenge of the current day.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("activeDaily")
    public Daily getTodaysDaily(){
        return this.dailyService.todaysDaily();
    }

    @Operation(summary = "Get all inactive Daily Challenges", description = "Retrieves all the Daily challenges, except today's Daily Challenge.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("inactiveDailys")
    public List<Daily> getInactiveDailys(){
        return this.dailyService.getAllDailysWithoutTodays();
    }

    @Operation(summary = "Create a new Daily Challenge", description = "Creates and saves a Daily Challenge in the database.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @PostMapping()
    public Daily createNewDaily(@RequestBody Daily newDaily){
        return this.dailyService.createNewDaily(newDaily);
    }

    @Operation(summary = "Delete a Daily Challenge", description = "Deletes a Daily Challenge from the database.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @DeleteMapping("{id}")
    public void deleteDaily(@PathVariable Long id){
        try{
            this.dailyService.deleteDaily(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Daily not found");
        }
    }


}
