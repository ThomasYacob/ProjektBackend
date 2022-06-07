package com.spring.backend.service;


import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Daily;
import com.spring.backend.repository.DailyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class DailyService {

    private final DailyRepository dailyRepository;

    @Autowired
    public DailyService(DailyRepository dailyRepository) {
        this.dailyRepository = dailyRepository;
    }

    public Daily createNewDaily(Daily daily){
        return this.dailyRepository.save(daily);
    }
    public Daily getDaily(Long id) throws ResourceNotFoundException{
        if(this.dailyRepository.findById(id).isPresent()){
            return this.dailyRepository.getById(id);
        }
        else throw new ResourceNotFoundException("Daily not found");
    }

    public void deleteDaily(Long id)throws ResourceNotFoundException{
        if(this.dailyRepository.findById(id).isPresent()){
            this.dailyRepository.deleteById(id);
        }
        else throw new ResourceNotFoundException("Daily not found");
    }

    public Daily todaysDaily(){
        Clock cl = Clock.systemUTC();
        LocalDate todaysDate = LocalDate.now(cl);
        Date date = Date.valueOf(todaysDate);
        return dailyRepository.findCurrentDaily(date);
    }

    public List<Daily> getAllDailysWithoutTodays(){
        List<Daily> dailyList = this.getAllDaily();
        dailyList.remove(this.todaysDaily());
        return dailyList;
    }

    public List<Daily> getAllDaily(){
        return this.dailyRepository.findAll();
    }


}