package com.spring.backend.controller;

import com.spring.backend.config.jwt.JwtUtils;

import com.spring.backend.model.Role;
import com.spring.backend.payload.request.LoginRequest;
import com.spring.backend.payload.request.SignupRequest;
import com.spring.backend.payload.request.TokenRefreshRequest;
import com.spring.backend.repository.RoleRepository;
import com.spring.backend.repository.UserRepository;
import com.spring.backend.service.RefreshTokenService;
import com.spring.backend.service.UserService;
import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @GetMapping("/all")
    public List<User> getAllUser(){
        return this.userService.findAllUsers();
    }

    @GetMapping("/{id}")
    User getUserByMail(@PathVariable Long id)throws ResponseStatusException {
        try {
            return this.userService.getUser(id);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

    @PostMapping()
    public ResponseEntity<?> createNewUser(@RequestBody SignupRequest user) {
        try {
            return userService.createNewUser(user);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id){
        try {
            this.userService.deleteUser(id);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User userdetails){
        try {
            return this.userService.updateUser(id, userdetails);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> logging(@RequestBody LoginRequest loginRequest) {
        try {
            return userService.logging(loginRequest);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }

    }

    @PutMapping("changeRole/{id}")
    User changeRoleUser(@PathVariable Long id,@RequestBody Role role){
        try {
            return this.userService.alterUserRole(id, role);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
//        try {
        return this.userService.refreshToken(request);
//        } catch (TokenRefreshException e) {
//            throw new TokenRefreshException(e, "Refresh token is not in database!");
//        }
    }
}