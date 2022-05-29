package com.spring.backend.controller;

import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Weekly;
import com.spring.backend.service.WeeklyService;
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
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/weekly")
public class WeeklyController {

    @Autowired
    private WeeklyService weeklyService;

    /**
     * REST API Method to get all Weekly assignments.
     */
    @GetMapping("all")
    public List<Weekly> getAllWeekly(){
        return this.weeklyService.getAllWeekly();
    }

    /**
     * REST API Method to get today's Weekly assignment.
     */
    @GetMapping("getWeekly")
    public Weekly getTodayWeekly(){
        return this.weeklyService.todaysWeekly();
    }

    /**
     * REST API Method to get Weekly assignment by ID.
     * @param id the ID of the weekly assignment.
     */
    @GetMapping("{id}")
    Weekly getWeeklyById(@PathVariable Long id){
        try {
            return this.weeklyService.getWeekly(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("weekly not found");
        }
    }

    /**
     * REST API Method to create a new Weekly assignment.
     * @param newWeekly the Weekly assignment to create.
     */
    @PostMapping()
    Weekly createNewWeekly(@RequestBody Weekly newWeekly){
        return this.weeklyService.createNewWeekly(newWeekly);
    }

    /**
     * REST API Method to delete Weekly assignment by ID.
     * @param id the ID of the Weekly assignment to delete.
     */
    @DeleteMapping("{id}")
    void deleteWeekly(@PathVariable Long id){
        try{
            this.weeklyService.deleteWeekly(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("weekly not found");
        }
    }
}
