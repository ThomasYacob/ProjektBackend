package com.spring.backend.controller;

import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Daily;
import com.spring.backend.service.DailyService;
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
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/daily")
public class DailyController {

    @Autowired
    private DailyService dailyService;

    /**
     * REST API Method to get all Daily questions.
     */
    @GetMapping("all")
    public List<Daily> getDaily(){
        return this.dailyService.getAllDaily();
    }

    /**
     * REST API Method to get Daily question by ID.
     * @param id the ID of the Daily question.
     */
    @GetMapping("{id}")
    public Daily getDailyById(@PathVariable Long id){
        try {
            return this.dailyService.getDaily(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Daily not found");
        }
    }

    /**
     * REST API Method to get today's Daily question.
     */
    @GetMapping("activeDaily")
    public Daily getTodaysDaily(){
        return this.dailyService.todaysDaily();
    }

    /**
     * REST API Method to get Inactive Daily questions.
     */
    @GetMapping("inactiveDailys")
    public List<Daily> getInactiveDailys(){
        return this.dailyService.getAllDailysWithoutTodays();
    }

    /**
     * REST API Method to create a new Daily question.
     * @param newDaily the Daily question to create.
     */
    @PostMapping()
    public Daily createNewDaily(@RequestBody Daily newDaily){
        return this.dailyService.createNewDaily(newDaily);
    }

    /**
     * REST API Method to delete Daily question by ID.
     * @param id the ID of the Daily question.
     */
    @DeleteMapping("{id}")
    public void deleteDaily(@PathVariable Long id){
        try{
            this.dailyService.deleteDaily(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Daily not found");
        }
    }


}
