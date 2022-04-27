package com.spring.backend.controller;

import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.User;
import com.spring.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class HelloWorldController {

    @Autowired
    private UserRepository userRepository;
    /*@Autowired
    private PasswordEncoder passwordEncoder;*/
    @Autowired
    private BCryptPasswordEncoder encodePassword;

    @GetMapping("/user")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
    }

    @PostMapping("/user")
    User createNewUser(@RequestBody User newUser){
        newUser.setPassword(encodePassword(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @DeleteMapping("user/{id}")
    void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @PutMapping("user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User userDetails){
        User updateUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
        updateUser.setUsername(userDetails.getUsername());
        updateUser.setPassword(userDetails.getPassword());
        updateUser.setEmail(userDetails.getEmail());
        return userRepository.save(updateUser);
    }

    @RequestMapping
    public String goodBye(){
        return "Hello from Spring Boot";
    }

    /*private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }*/

    private String encodePassword(String password) {
        return encodePassword.encode(password);
    }
}