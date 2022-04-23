package com.spring.backend.model;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name = "userID")
    private int id;
    @Column(name = "username", nullable = false, length = 512,unique = true)
    private String username;
    @Column(name = "password" ,nullable = false, length = 512)
    private String password;
    @Column(name = "mail", nullable = false, length = 512)
    private String mail;

    /*@OneToOne
    @JoinTable(name = "userScoreboard",
            joinColumns =
                    {@JoinColumn(name = "userId", referencedColumnName = "Userid")},
            inverseJoinColumns =
                    {@JoinColumn(name= "scoreId",referencedColumnName = "scoreID")})
    private Scoreboard scoreboard;*/
}
