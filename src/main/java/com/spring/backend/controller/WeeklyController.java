package com.spring.backend.controller;


import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Weekly;
import com.spring.backend.service.WeeklyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/weekly")
public class WeeklyController {

    @Autowired
    private WeeklyService weeklyService;

    @GetMapping("all")
    public List<Weekly> getAllWeekly(){
        return this.weeklyService.getAllWeekly();
    }

    @GetMapping("getWeekly")
    public Weekly getTodayWeekly(){
        return this.weeklyService.todaysWeekly();
    }

    @GetMapping("{id}")
    public Weekly getWeeklyById(@PathVariable Long id){
        try {
            return this.weeklyService.getWeekly(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("weekly not found");
        }
    }

    @PostMapping()
    public Weekly createNewWeekly(@RequestBody Weekly newMonthly){
        return this.weeklyService.createNewWeekly(newMonthly);
    }

    @DeleteMapping("{id}")
    public void deleteWeekly(@PathVariable Long id){
        try{
            this.weeklyService.deleteWeekly(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("weekly not found");
        }
    }
}
