package com.spring.backend.controller;

import com.spring.backend.service.MonthlyService;
import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Monthly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/monthly")
public class MonthlyController {

    @Autowired
    private MonthlyService monthlyService;

    @GetMapping("all")
    public List<Monthly> getAllMonthly(){
        return this.monthlyService.getAllMonthly();
    }

    @GetMapping("{id}")
    Monthly getMonthlyById(@PathVariable Long id){
        try {
            return this.monthlyService.getMonthly(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Monthly not found");
        }
    }

    @PostMapping()
    Monthly createNewMonthly(@RequestBody Monthly newMonthly){
        return this.monthlyService.createNewMonthly(newMonthly);
    }

    @DeleteMapping("{id}")
    void deleteMonthly(@PathVariable Long id){
        try{
            this.monthlyService.deleteMonthly(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Monthly not found");
        }
    }


}
