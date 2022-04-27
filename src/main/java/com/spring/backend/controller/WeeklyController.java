package com.spring.backend.controller;


import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Weekly;
import com.spring.backend.service.WeeklyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("api/weekly")
public class WeeklyController {

    @Autowired
    private WeeklyService weeklyService;

    @GetMapping("all")
    public List<Weekly> getAllWeekly(){
        return this.weeklyService.getAllWeekly();
    }

    @GetMapping("{id}")
    Weekly getWeeklyById(@PathVariable int id){
        try {
            return this.weeklyService.getWeekly(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("weekly not found");
        }
    }

    @PostMapping()
    Weekly createNewWeekly(@RequestBody Weekly newMonthly){
        return this.weeklyService.createNewWeekly(newMonthly);
    }

    @DeleteMapping("{id}")
    void deleteWeekly(@PathVariable int id){
        try{
            this.weeklyService.deleteWeekly(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("weekly not found");
        }
    }
}