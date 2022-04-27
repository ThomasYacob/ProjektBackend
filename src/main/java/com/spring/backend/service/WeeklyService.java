package com.spring.backend.service;

import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Weekly;
import com.spring.backend.repository.WeeklyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeeklyService {

    private final WeeklyRepository weeklyRepository;
    @Autowired
    public WeeklyService(WeeklyRepository weeklyRepository){
        this.weeklyRepository = weeklyRepository;
    }

    public Weekly createNewWeekly(Weekly weekly){
        return this.weeklyRepository.save(weekly);
    }

    public Weekly getWeekly(int id) throws ResourceNotFoundException {
        if(this.weeklyRepository.findById(id).isPresent()){
            return this.weeklyRepository.getById(id);
        }
        else throw new ResourceNotFoundException("Weekly not found");
    }
    public void deleteWeekly(int id) throws ResourceNotFoundException{
        if(this.weeklyRepository.findById(id).isPresent()){
            this.weeklyRepository.deleteById(id);
        }
        else throw new ResourceNotFoundException("Weekly not found");
    }

    public List<Weekly> getAllWeekly(){
        return this.weeklyRepository.findAll();
    }
}
