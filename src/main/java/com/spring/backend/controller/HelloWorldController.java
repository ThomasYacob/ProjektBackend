package com.spring.backend.controller;

import com.spring.backend.config.AuthenticationBean;
import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.User;
import com.spring.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class HelloWorldController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(encodePassword(newUser.getPassword()));
        user.setEmail(newUser.getEmail());
        return userRepository.save(user);
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

    @GetMapping("/basicauth")
    public AuthenticationBean basicauth() {
        return new AuthenticationBean("You are authenticated");
    }

    @RequestMapping
    public String goodBye(){
        return "Hello from Spring Boot";
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}