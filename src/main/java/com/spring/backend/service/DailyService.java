package com.spring.backend.service;


import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Daily;
import com.spring.backend.repository.DailyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
    public Daily getDaily(int id) throws ResourceNotFoundException{
        if(this.dailyRepository.findById(id).isPresent()){
            return this.dailyRepository.getById(id);
        }
        else throw new ResourceNotFoundException("Daily not found");
    }

    public void deleteDaily(int id)throws ResourceNotFoundException{
        if(this.dailyRepository.findById(id).isPresent()){
            this.dailyRepository.deleteById(id);
        }
        else throw new ResourceNotFoundException("Daily not found");
    }

    public List<Daily> getAllDaily(){
        return this.dailyRepository.findAll();
    }


}
