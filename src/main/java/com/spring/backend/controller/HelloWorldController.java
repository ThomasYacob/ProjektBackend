package com.spring.backend.controller;

import com.spring.backend.model.User;
import com.spring.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

public class HelloWorldController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    @PostMapping("/user")
    User createNewUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @DeleteMapping("user/{id}")
    void deleteuser(@PathVariable int id){
        userRepository.deleteById(id);
    }


    @RequestMapping
    public String goodBye(){
        return "Hello from Spring Boot";
    }
}