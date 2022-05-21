package com.spring.backend.service;

import com.spring.backend.config.CustomUserDetails;
import com.spring.backend.config.jwt.JwtUtils;
import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.exceptions.TokenRefreshException;
import com.spring.backend.model.*;
import com.spring.backend.payload.request.LoginRequest;
import com.spring.backend.payload.request.SignupRequest;
import com.spring.backend.payload.request.TokenRefreshRequest;
import com.spring.backend.payload.response.JwtResponse;
import com.spring.backend.payload.response.MessageResponse;
import com.spring.backend.payload.response.TokenRefreshResponse;
import com.spring.backend.repository.RoleRepository;
import com.spring.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ScoreBoardService scoreBoardService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository,ScoreBoardService scoreBoardService,BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.scoreBoardService = scoreBoardService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }

    public void deleteUser(Long id) throws ResourceNotFoundException{
        this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
    }

    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
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
        Scoreboard temp = new Scoreboard(user);
        userRepository.save(user);
        scoreBoardService.addScoreBoard(temp);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public User getUser(Long id) throws  ResourceNotFoundException{
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User updateUser(Long id, User userDetails){
        User updateUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
        updateUser.setUsername(userDetails.getUsername());
        updateUser.setPassword(userDetails.getPassword());
        updateUser.setEmail(userDetails.getEmail());
        updateUser.setRoles(userDetails.getRoles());
        return userRepository.save(updateUser);
    }

    public User alterUserRole(Long id, Role role) throws  ResourceNotFoundException{
        if (this.userRepository.findById(id).isPresent())
        {
            User userToBeChanged = userRepository.getById(id);
            userToBeChanged.setRoles((Set<Role>) role);
            User updatedUser = userRepository.save(userToBeChanged);
            return updatedUser;
        }
        else throw new ResourceNotFoundException("User not found");

    }

}
