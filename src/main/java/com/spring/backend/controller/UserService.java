package com.spring.backend.controller;

import com.spring.backend.model.Scoreboard;
import com.spring.backend.model.User;
import com.spring.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    private UserRepository userRepository;
    private ScoreBoardService scoreBoardService;

    @Autowired
    public UserService(UserRepository userRepository,ScoreBoardService scoreBoardService){
        this.userRepository = userRepository;
        this.scoreBoardService = scoreBoardService;
    }

    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    public void deleteUser(String id){
        this.userRepository.deleteById(id);
    }

    public User createNewUser(User user){
        Scoreboard scoreboard = new Scoreboard(user);
        this.userRepository.save(user);
        scoreBoardService.addScoreBoard(scoreboard);
        return user;
    }

    public User getUser(String email){
        if (this.userRepository.findById(email).isPresent())
        {
            return this.userRepository.findById(email).get();
        }
        return null;
    }

}
