package com.spring.backend.controller;

import com.spring.backend.model.User;
import com.spring.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("api/user")

public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("all")
    public List<User> getAllUser(){
        return userService.findAll();
    }
    @GetMapping("{id}")
    User getUserByMail(@PathVariable String id){
        return userService.getUser(id);
    }


    @PostMapping()
    User createNewUser(@RequestBody User newUser){
        return userService.createNewUser(newUser);
    }

    @DeleteMapping("{id}")
    void deleteUser(@PathVariable String id){
        userService.deleteUser(id);
    }

}