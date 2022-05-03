package com.spring.backend.service;



import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Monthly;
import com.spring.backend.repository.MonthlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlyService {

    private final MonthlyRepository monthlyRepository;
    @Autowired
    public MonthlyService(MonthlyRepository monthlyRepository){
        this.monthlyRepository = monthlyRepository;
    }

    public Monthly createNewMonthly(Monthly monthly){
        return this.monthlyRepository.save(monthly);
    }

    public Monthly getMonthly(int id) throws ResourceNotFoundException{
        if(this.monthlyRepository.findById(id).isPresent()){
            return this.monthlyRepository.getById(id);
        }
        else throw new ResourceNotFoundException("Monthly not found");
    }
    public void deleteMonthly(int id) throws ResourceNotFoundException{
        if(this.monthlyRepository.findById(id).isPresent()){
            this.monthlyRepository.deleteById(id);
        }
        else throw new ResourceNotFoundException("Monthly not found");
    }

    public String findAnswerById(int id) throws ResourceNotFoundException{
        if(this.monthlyRepository.findById(id).isPresent()){
            return this.monthlyRepository.getById(id).getAnswer();
        }
        else throw new ResourceNotFoundException();
    }

    public List<Monthly> getAllMonthly(){
        return this.monthlyRepository.findAll();
    }
}
