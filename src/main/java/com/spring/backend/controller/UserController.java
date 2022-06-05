package com.spring.backend.controller;

import com.spring.backend.config.jwt.JwtUtils;
import com.spring.backend.model.Daily;
import com.spring.backend.model.Role;
import com.spring.backend.payload.request.LogOutRequest;
import com.spring.backend.payload.request.LoginRequest;
import com.spring.backend.payload.request.SignupRequest;
import com.spring.backend.payload.request.TokenRefreshRequest;
import com.spring.backend.payload.response.MessageResponse;
import com.spring.backend.repository.RoleRepository;
import com.spring.backend.repository.ScoreboardRepository;
import com.spring.backend.repository.UserRepository;
import com.spring.backend.service.RefreshTokenService;
import com.spring.backend.service.ScoreBoardService;
import com.spring.backend.service.UserService;
import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * The controller class of the "User" REST API methods. This class is responsible
 * for processing incoming HTTP requests, preparing a model and returning
 * a response.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Tag(name = "user", description = "Operations about User")
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
    ScoreboardRepository scoreboardRepository;

    @Autowired
    ScoreBoardService scoreBoardService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Operation(summary = "Get Users", description = "Retrieves all Users saved in the database.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("/all")
    public List<User> getAllUser(){
        return this.userService.findAllUsers();
    }

    @Operation(summary = "Get User", description = "Retrieves a specific User, based on User ID.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @GetMapping("/{id}")
    User getUserByMail(@PathVariable Long id)throws ResponseStatusException {
        try {
            return this.userService.getUser(id);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Operation(summary = "Create User", description = "Creates and saves a User in the database.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @PostMapping()
    public ResponseEntity<?> createNewUser(@RequestBody SignupRequest user) {
        try {
            return userService.createNewUser(user);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Operation(summary = "Delete User", description = "Deletes a specific User from the database, based on User ID.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") long id){
        try {
            return userService.deleteUser(id);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Operation(summary = "Update User", description = "Updates the information in the database about a specific User, based on User ID.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        try {
            return this.userService.updateUser(id, user);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Operation(summary = "Update User Role", description = "Updates the Role of a specific User, based on User ID.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @PutMapping("/changeRole/{id}")
    User changeRoleUser(@PathVariable Long id,@RequestBody Role role){
        try {
            return this.userService.alterUserRole(id, role);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Operation(summary = "Login", description = "Handles the login and authentication for a User.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @PostMapping("/login")
    public ResponseEntity<?> logging(@RequestBody LoginRequest loginRequest) {
        try {
            return userService.login(loginRequest);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Operation(summary = "Get Refresh Token", description = "Acquires a new Refresh Token.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        return this.userService.refreshToken(request);
    }

    @Operation(summary = "Logout", description = "Handles the logout and marks the user as unauthenticated.",
            responses = {@ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Daily.class)))})
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
        refreshTokenService.deleteByUserId(logOutRequest.getUserId());
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }
}