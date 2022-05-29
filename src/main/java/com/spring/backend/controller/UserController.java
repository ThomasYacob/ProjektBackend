package com.spring.backend.controller;

import com.spring.backend.config.jwt.JwtUtils;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


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

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") long id){
        try {
            return userService.deleteUser(id);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, Role role, @RequestBody User user) {
        try {
            return this.userService.updateUser(id, user);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,
//                                                   @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
//
//        user.setUsername(userDetails.getUsername());
//        user.setEmail(userDetails.getEmail());
//        user.setPassword(userDetails.getPassword());
//        user.setRoles(userDetails.getRoles());
//        final User updatedUser = userRepository.save(user);
//        return ResponseEntity.ok(updatedUser);
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
//        Optional<User> userData = userRepository.findById(id);
//
//        if(userData.isPresent()) {
//            User _user = userData.get();
//            _user.setUsername(user.getUsername());
//            _user.setEmail(user.getEmail());
//            _user.setPassword(user.getPassword());
//            _user.setRoles(user.getRoles());
//            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @PutMapping("/changeRole/{id}")
    User changeRoleUser(@PathVariable Long id,@RequestBody Role role){
        try {
            return this.userService.alterUserRole(id, role);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("User not found");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> logging(@RequestBody LoginRequest loginRequest) {
        try {
            return userService.login(loginRequest);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        return this.userService.refreshToken(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
        refreshTokenService.deleteByUserId(logOutRequest.getUserId());
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }
}