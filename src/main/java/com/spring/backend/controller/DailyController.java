package com.spring.backend.controller;


import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Daily;
import com.spring.backend.model.Monthly;
import com.spring.backend.repository.DailyRepository;
import com.spring.backend.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/daily")
public class DailyController {

    @Autowired
    private DailyService dailyService;

    @GetMapping("all")
    public List<Daily> getDaily(){
        return this.dailyService.getAllDaily();
    }

    @GetMapping("{id}")
    public Daily getDailyById(@PathVariable Long id){
        try {
            return this.dailyService.getDaily(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Daily not found");
        }
    }

    @GetMapping("activeDaily")
    public Daily getTodaysDaily(){
        return this.dailyService.todaysDaily();
    }

    @GetMapping("inactiveDailys")
    public List<Daily> getInactiveDailys(){
        return this.dailyService.getAllDailysWithoutTodays();
    }

    @PostMapping()
    public Daily createNewDaily(@RequestBody Daily newDaily){
        return this.dailyService.createNewDaily(newDaily);
    }

    @DeleteMapping("{id}")
    public void deleteDaily(@PathVariable Long id){
        try{
            this.dailyService.deleteDaily(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Daily not found");
        }
    }


}
