package com.spring.backend.service;

import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Scoreboard;
import com.spring.backend.model.User;
import com.spring.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ScoreBoardService scoreBoardService;

    @Autowired
    public UserService(UserRepository userRepository,ScoreBoardService scoreBoardService){
        this.userRepository = userRepository;
        this.scoreBoardService = scoreBoardService;
    }

    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }

    public void deleteUser(String email) throws ResourceNotFoundException{
        if (this.userRepository.findById(email).isPresent())
        {
            this.userRepository.deleteById(email);
        }
        else throw new ResourceNotFoundException("User not found");
    }

    public User createNewUser(User user){
        Scoreboard scoreboard = new Scoreboard(user);
        this.userRepository.save(user);
        scoreBoardService.addScoreBoard(scoreboard);
        return user;
    }

    public User getUser(String email) throws  ResourceNotFoundException{
        if (this.userRepository.findById(email).isPresent())
        {
            return this.userRepository.getById(email);
        }
        else throw new ResourceNotFoundException("User not found");
    }

}
