package com.spring.backend.service;

import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Weekly;
import com.spring.backend.repository.WeeklyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

/**
 * This service layer holds the business logic for the WeeklyController class.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Service
public class WeeklyService {

    private final WeeklyRepository weeklyRepository;

    /**
     * Creates a WeeklyRepository for this service layer,
     * in order to perform CRUD operations on the database.
     * @param weeklyRepository the Repository for the Weekly Objects.
     */
    @Autowired
    public WeeklyService(WeeklyRepository weeklyRepository){
        this.weeklyRepository = weeklyRepository;
    }

    /**
     * Saves the Weekly assignment in the database.
     * @param weekly the Weekly assignment.
     */
    public Weekly createNewWeekly(Weekly weekly){
        return this.weeklyRepository.save(weekly);
    }

    /**
     * Get a specific Weekly assignment from the database, based on ID.
     * @param id the ID for the Weekly assignment.
     * @throws ResourceNotFoundException
     */
    public Weekly getWeekly(Long id) throws ResourceNotFoundException {
        if(this.weeklyRepository.findById(id).isPresent()){
            return this.weeklyRepository.getById(id);
        }
        else throw new ResourceNotFoundException("Weekly not found");
    }

    /**
     * Deletes a specific Weekly assignment from the database, based on ID.
     * @param id the ID for the Weekly assignment.
     * @throws ResourceNotFoundException
     */
    public void deleteWeekly(Long id) throws ResourceNotFoundException{
        if(this.weeklyRepository.findById(id).isPresent()){
            this.weeklyRepository.deleteById(id);
        }
        else throw new ResourceNotFoundException("Weekly not found");
    }

    /**
     * Get the Weekly assignment for the current week from the database.
     */
    public Weekly todaysWeekly(){
        Date todaysDate = new Date(LocalDate.now().getYear(),LocalDate.now().getDayOfMonth(),LocalDate.now().getDayOfYear());
        return weeklyRepository.findCurrentWeekly(todaysDate);
    }

    /**
     * Get all the Weekly assignment from the database.
     */
    public List<Weekly> getAllWeekly(){
        return this.weeklyRepository.findAll();
    }
}
