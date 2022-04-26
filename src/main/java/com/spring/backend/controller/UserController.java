package com.spring.backend.controller;

import com.spring.backend.service.UserService;
import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("api/user")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("all")
    public List<User> getAllUser(){
        return this.userService.findAllUsers();
    }
    @GetMapping("{id}")
    User getUserByMail(@PathVariable String id)throws ResponseStatusException {
        try{
            return this.userService.getUser(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }


    @PostMapping()
    User createNewUser(@RequestBody User newUser){
        return userService.createNewUser(newUser);
    }

    @DeleteMapping("{id}")
    void deleteUser(@PathVariable String id){
        try{
            this.userService.deleteUser(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

}