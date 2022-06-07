package com.spring.backend.service;

import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Monthly;
import com.spring.backend.repository.MonthlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

/**
 * This service layer holds the business logic for the MonthlyController class.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Service
public class MonthlyService {

    private final MonthlyRepository monthlyRepository;

    /**
     * Creates a DailyRepository for this service layer,
     * in order to perform CRUD operations on the database.
     * @param monthlyRepository the Repository for the Monthly Objects.
     */
    @Autowired
    public MonthlyService(MonthlyRepository monthlyRepository){
        this.monthlyRepository = monthlyRepository;
    }

    /**
     * Saves the Monthly assignment in the database.
     * @param monthly the Monthly assignment.
     */
    public Monthly createNewMonthly(Monthly monthly){
        return this.monthlyRepository.save(monthly);
    }

    /**
     * Get a Monthly assignment from the database, based on ID.
     * @param id the ID for the Monthly assignment.
     * @throws ResourceNotFoundException
     */
    public Monthly getMonthly(Long id) throws ResourceNotFoundException{
        if(this.monthlyRepository.findById(id).isPresent()){
            return this.monthlyRepository.getById(id);
        }
        else throw new ResourceNotFoundException("Monthly not found");
    }

    /**
     * Deletes a specific Monthly assignment from the database, based on ID.
     * @param id the ID of the Monthly assignment.
     * @throws ResourceNotFoundException
     */
    public void deleteMonthly(Long id) throws ResourceNotFoundException{
        if(this.monthlyRepository.findById(id).isPresent()){
            this.monthlyRepository.deleteById(id);
        }
        else throw new ResourceNotFoundException("Monthly not found");
    }

    /**
     * Get the answer for a Monthly assignment from the database, based on the ID.
     * @param id the ID for the Monthly assignment.
     * @throws ResourceNotFoundException
     */
    public String findAnswerById(Long id) throws ResourceNotFoundException{
        if(this.monthlyRepository.findById(id).isPresent()){
            return this.monthlyRepository.getById(id).getAnswer();
        }
        else throw new ResourceNotFoundException();
    }

    /**
     * Get the Monthly assignment from the database, for the current month.
     */
    public Monthly findMonthlyActive(){
        List<Monthly> monthlyList = this.monthlyRepository.findAll();
        Clock cl = Clock.systemUTC();
        int year = LocalDate.now(cl).getYear();
        int month = LocalDate.now(cl).getMonthValue();
        for (Monthly monthly: monthlyList) {
            if(monthly.getDate().getMonth() == month && monthly.getDate().getYear() == year)
            {
                return monthly;
            }
        }
        return null;
    }

    /**
     * Get all the Monthly assignment from the database,
     * except the current month.
     */
    public List<Monthly> findAllMonthlysExceptCurrent(){
        List<Monthly> monthlyList = this.monthlyRepository.findAll();
        monthlyList.remove(this.findMonthlyActive());
        return monthlyList;
    }

    /**
     * Get all the Monthly assignment from the database.
     */
    public List<Monthly> getAllMonthly(){
        return this.monthlyRepository.findAll();
    }
}
