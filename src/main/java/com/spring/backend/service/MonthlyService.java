package com.spring.backend.service;



import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Monthly;
import com.spring.backend.repository.MonthlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
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

    public Monthly getMonthly(Long id) throws ResourceNotFoundException{
        if(this.monthlyRepository.findById(id).isPresent()){
            return this.monthlyRepository.getById(id);
        }
        else throw new ResourceNotFoundException("Monthly not found");
    }
    public void deleteMonthly(Long id) throws ResourceNotFoundException{
        if(this.monthlyRepository.findById(id).isPresent()){
            this.monthlyRepository.deleteById(id);
        }
        else throw new ResourceNotFoundException("Monthly not found");
    }

    public String findAnswerById(Long id) throws ResourceNotFoundException{
        if(this.monthlyRepository.findById(id).isPresent()){
            return this.monthlyRepository.getById(id).getAnswer();
        }
        else throw new ResourceNotFoundException();
    }

    public Monthly findMonthlyActive(){
        List<Monthly> monthlyList = this.monthlyRepository.findAll();
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        for (Monthly monthly: monthlyList) {
            if(monthly.getDate().getMonth()== month && monthly.getDate().getYear() == year)
            {
                return monthly;
            }
        }
        return null;
    }

    public List<Monthly> findAllMonthlysExceptCurrent(){
        List<Monthly> monthlyList = this.monthlyRepository.findAll();
        monthlyList.remove(this.findMonthlyActive());
        return monthlyList;
    }


    public List<Monthly> getAllMonthly(){
        return this.monthlyRepository.findAll();
    }
}
