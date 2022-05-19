package com.spring.backend.controller;

import com.spring.backend.config.CustomUserDetails;
import com.spring.backend.config.jwt.JwtUtils;
import com.spring.backend.exceptions.TokenRefreshException;
import com.spring.backend.model.ERole;
import com.spring.backend.model.RefreshToken;
import com.spring.backend.model.Role;
import com.spring.backend.payload.request.LoginRequest;
import com.spring.backend.payload.request.SignupRequest;
import com.spring.backend.payload.request.TokenRefreshRequest;
import com.spring.backend.payload.response.JwtResponse;
import com.spring.backend.payload.response.MessageResponse;
import com.spring.backend.payload.response.TokenRefreshResponse;
import com.spring.backend.repository.RoleRepository;
import com.spring.backend.repository.UserRepository;
import com.spring.backend.service.RefreshTokenService;
import com.spring.backend.service.UserService;
import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

//    @GetMapping("/{id}")
//    User getUserByMail(@PathVariable String id)throws ResponseStatusException {
//        try{
//            return this.userService.getUser(id);
//        }catch (ResourceNotFoundException e){
//            throw new ResourceNotFoundException("User not found");
//        }
//    }

//    @PostMapping()
//    User createNewUser(@RequestBody User newUser){
//        return userService.createNewUser(newUser);
//    }
    @PostMapping()
    public ResponseEntity<?> createNewUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(signupRequest.getUsername(), signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.Admin)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.Admin)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ContentCreator)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.User)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

//    @DeleteMapping("/{id}")
//    void deleteUser(@PathVariable String id){
//        try{
//            this.userService.deleteUser(id);
//        }catch (ResourceNotFoundException e){
//            throw new ResourceNotFoundException("User not found");
//        }
//    }

//    @PutMapping("/{id}")
//    public User updateUser(@PathVariable String id,@RequestBody User userdetails){
//        try {
//            return this.userService.updateUser(id,userdetails);
//        }catch (ResourceNotFoundException e){
//            throw new ResourceNotFoundException("User not found");
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<?> logging(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(customUserDetails);

        List<String> roles = customUserDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(customUserDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), customUserDetails.getId(),
                customUserDetails.getUsername(), customUserDetails.getEmail(), roles));
    }

//    @PutMapping("changeRole/{id}")
//    User changeRoleUser(@PathVariable String id,@RequestBody Role role){
//        try{
//            return this.userService.alterUserRole(id,role);
//        }catch (ResourceNotFoundException e){
//            throw new ResourceNotFoundException("User not found");
//        }
//    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}