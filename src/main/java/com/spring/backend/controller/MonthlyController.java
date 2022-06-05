package com.spring.backend.controller;

import com.spring.backend.model.Daily;
import com.spring.backend.service.MonthlyService;
import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Monthly;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The controller class of the "Monthly" REST API methods. This class is responsible
 * for processing incoming HTTP requests, preparing a model and returning
 * a response.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Tag(name = "monthly", description = "Operations about Monthly Challenges.")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/monthly")
public class MonthlyController {

    @Autowired
    private MonthlyService monthlyService;

    @Operation(summary = "Get Monthly Challenges", description = "Retrieves all Monthly Challenges saved in the database.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("all")
    public List<Monthly> getAllMonthly(){
        return this.monthlyService.getAllMonthly();
    }

    @Operation(summary = "Get Monthly Challenge by ID", description = "Retrieves a specific Monthly Challenge, based on ID.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("{id}")
    Monthly getMonthlyById(@PathVariable Long id){
        try {
            return this.monthlyService.getMonthly(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Monthly not found");
        }
    }

    @Operation(summary = "Create a new Monthly Challenge", description = "Creates and saves a Monthly Challenge in the database.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @PostMapping()
    Monthly createNewMonthly(@RequestBody Monthly newMonthly){
        return this.monthlyService.createNewMonthly(newMonthly);
    }

    @Operation(summary = "Get the Monthly Challenge", description = "Retrieves the Monthly Challenge of the current month.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("activeMonthly")
    public Monthly getActiveMonthly(){
        return this.monthlyService.findMonthlyActive();
    }

    @Operation(summary = "Get inactive Monthly Challenges", description = "Retrieves all the Monthly challenges, except this months Monthly Challenge.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("inactiveMonthlys")
    public List<Monthly> getInactiveMonthly(){
        return this.monthlyService.findAllMonthlysExceptCurrent();
    }

    @Operation(summary = "Delete a Monthly Challenge", description = "Deletes a Monthly Challenge from the database.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @DeleteMapping("{id}")
    void deleteMonthly(@PathVariable Long id){
        try{
            this.monthlyService.deleteMonthly(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Monthly not found");
        }
    }
}