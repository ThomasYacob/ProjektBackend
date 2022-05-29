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
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

/**
 * The controller class of the "User" REST API methods. This class is responsible
 * for processing incoming HTTP requests, preparing a model and returning
 * a response.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    /**
     * REST API Method to get all Users.
     */
    @GetMapping("/all")
    public List<User> getAllUser(){
        return this.userService.findAllUsers();
    }

    /**
     * REST API Method to get User by ID.
     * @param id the ID of the User.
     */
    @GetMapping("/{id}")
    User getUserByMail(@PathVariable Long id)throws ResponseStatusException {
        try {
            return this.userService.getUser(id);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

    /**
     * REST API Method to create a new User.
     * @param user the new User to create.
     */
    @PostMapping()
    public ResponseEntity<?> createNewUser(@RequestBody SignupRequest user) {
        try {
            return userService.createNewUser(user);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    /**
     * Method to initiate the Roles in the system.
     */
    @PostConstruct
    public void inserts(){
    userService.initiateRoles();
    }

    /**
     * REST API Method to delete an existing User.
     * @param id the ID of the User to delete.
     */
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id){
        try {
            this.userService.deleteUser(id);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

    /**
     * REST API Method to update information of an existing User.
     * @param id the ID of the User to update.
     * @param userdetails the new details of the User.
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User userdetails){
        try {
            return this.userService.updateUser(id, userdetails);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

    /**
     * REST API Method that handle login request.
     * @param loginRequest consist of username and password.
     */
    @PostMapping("/login")
    public ResponseEntity<?> logging(@RequestBody LoginRequest loginRequest) {
        try {
            return userService.logging(loginRequest);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }

    }

    /**
     * REST API Method to update the Role of an existing User.
     * @param id the ID of the User to update.
     * @param role the new Role.
     */
    @PutMapping("changeRole/{id}")
    User changeRoleUser(@PathVariable Long id,@RequestBody Role role){
        try {
            return this.userService.alterUserRole(id, role);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

    /**
     * REST API Method that acquires the new access Token.
     * @param request the HTTP request to obtain additional access token.
     */
    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
//        try {
        return this.userService.refreshToken(request);
//        } catch (TokenRefreshException e) {
//            throw new TokenRefreshException(e, "Refresh token is not in database!");
//        }
    }
}