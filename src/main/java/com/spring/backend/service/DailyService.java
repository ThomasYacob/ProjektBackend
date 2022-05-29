package com.spring.backend.service;

import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Daily;
import com.spring.backend.repository.DailyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * This service layer holds the business logic for the DailyController class.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Service
public class DailyService {

    private final DailyRepository dailyRepository;

    /**
     * Creates a DailyRepository for this service layer,
     * in order to perform CRUD operations on the database.
     * @param dailyRepository the Repository for the Daily Objects.
     */
    @Autowired
    public DailyService(DailyRepository dailyRepository) {
        this.dailyRepository = dailyRepository;
    }

    /**
     * Saves the daily question in the database.
     * @param daily the Daily question.
     */
    public Daily createNewDaily(Daily daily){
        return this.dailyRepository.save(daily);
    }

    /**
     * Get a specific Daily question from the database, based on ID.
     * @param id the ID of the Daily question.
     * @throws ResourceNotFoundException
     */
    public Daily getDaily(Long id) throws ResourceNotFoundException{
        if(this.dailyRepository.findById(id).isPresent()){
            return this.dailyRepository.getById(id);
        }
        else throw new ResourceNotFoundException("Daily not found");
    }

    /**
     * Deletes a specific Daily question from the database, based on ID.
     * @param id the ID of the Daily question.
     * @throws ResourceNotFoundException
     */
    public void deleteDaily(Long id)throws ResourceNotFoundException{
        if(this.dailyRepository.findById(id).isPresent()){
            this.dailyRepository.deleteById(id);
        }
        else throw new ResourceNotFoundException("Daily not found");
    }

    /**
     * Get the Daily question of the current day from the database.
     */
    public Daily todaysDaily(){
        Date todaysDate = new Date(LocalDate.now().getYear(),LocalDate.now().getDayOfMonth(),LocalDate.now().getDayOfYear());
        return dailyRepository.findCurrentDaily(todaysDate);
    }

    /**
     * Get all the Daily questions from the database,
     * except today's Daily question.
     */
    public List<Daily> getAllDailysWithoutTodays(){
        List<Daily> dailyList = this.getAllDaily();
        dailyList.remove(this.todaysDaily());
        return dailyList;
    }

    /**
     * Get all the Daily questions from the database.
     */
    public List<Daily> getAllDaily(){
        return this.dailyRepository.findAll();
    }


}
