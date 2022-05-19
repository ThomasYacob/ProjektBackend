package com.spring.backend.service;

import com.spring.backend.exceptions.ResourceNotFoundException;
import com.spring.backend.model.Role;
import com.spring.backend.model.Scoreboard;
import com.spring.backend.model.User;
import com.spring.backend.repository.RoleDao;
import com.spring.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ScoreBoardService scoreBoardService;
    private final BCryptPasswordEncoder passwordEncoder;

//    @Autowired
//    private RoleDao roleDao;

//    public void initRole() {
//        Role userRole = new Role();
//        userRole.setName("User");
//        roleDao.save(userRole);
//
//        Role contentCreatorRole = new Role();
//        contentCreatorRole.setName("ContentCreator");
//        roleDao.save(contentCreatorRole);
//
//        Role adminRole = new Role();
//        adminRole.setName("Admin");
//        roleDao.save(adminRole);
//    }

    @Autowired
    public UserService(UserRepository userRepository,ScoreBoardService scoreBoardService,BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.scoreBoardService = scoreBoardService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }

    public void deleteUser(String email) throws ResourceNotFoundException{
        this.userRepository.findById(Long.valueOf(email)).orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + email));
    }

    public User createNewUser(User user){
        Scoreboard scoreboard = new Scoreboard(user);
        user.setPassword(encodePassword(user.getPassword()));
        this.userRepository.save(user);
        scoreBoardService.addScoreBoard(scoreboard);
        return user;
    }

    public User getUser(String email) throws  ResourceNotFoundException{
        return userRepository.findById(Long.valueOf(email)).orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + email));
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User updateUser(String email,User userDetails){
        User updateUser = userRepository.findById(Long.valueOf(email)).orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + email));
        updateUser.setUsername(userDetails.getUsername());
        updateUser.setPassword(userDetails.getPassword());
        updateUser.setEmail(userDetails.getEmail());
        updateUser.setRoles(userDetails.getRoles());
        return userRepository.save(updateUser);
    }

    public User alterUserRole(String email, Role role) throws  ResourceNotFoundException{
        if (this.userRepository.findById(Long.valueOf(email)).isPresent())
        {
            User userToBeChanged = userRepository.getById(Long.valueOf(email));
            userToBeChanged.setRoles((Set<Role>) role);
            User updatedUser = userRepository.save(userToBeChanged);
            return updatedUser;
        }
        else throw new ResourceNotFoundException("User not found");

    }

}
