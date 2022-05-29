package com.spring.backend.controller;

import com.spring.backend.service.MonthlyService;
import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Monthly;
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
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/monthly")
public class MonthlyController {

    @Autowired
    private MonthlyService monthlyService;

    /**
     * REST API Method to get all Monthly assignments.
     */
    @GetMapping("all")
    public List<Monthly> getAllMonthly(){
        return this.monthlyService.getAllMonthly();
    }

    /**
     * REST API Method to get Monthly assignment by ID.
     * @param id the ID of the Monthly assignment.
     */
    @GetMapping("{id}")
    Monthly getMonthlyById(@PathVariable Long id){
        try {
            return this.monthlyService.getMonthly(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Monthly not found");
        }
    }

    /**
     * REST API Method to create a new Monthly assignment.
     * @param newMonthly the Monthly assignment to create.
     */
    @PostMapping()
    Monthly createNewMonthly(@RequestBody Monthly newMonthly){
        return this.monthlyService.createNewMonthly(newMonthly);
    }

    /**
     * REST API Method to get active Monthly assignments.
     */
    @GetMapping("activeMonthly")
    public Monthly getActiveMonthly(){
        return this.monthlyService.findMonthlyActive();
    }

    /**
     * REST API Method to get inactive Monthly assignments.
     */
    @GetMapping("inactiveMonthlys")
    public List<Monthly> getInactiveMonthly(){
        return this.monthlyService.findAllMonthlysExceptCurrent();
    }

    /**
     * REST API Method to delete Monthly assignment by ID.
     * @param id the ID of the Monthly assignment.
     */
    @DeleteMapping("{id}")
    void deleteMonthly(@PathVariable Long id){
        try{
            this.monthlyService.deleteMonthly(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Monthly not found");
        }
    }
}