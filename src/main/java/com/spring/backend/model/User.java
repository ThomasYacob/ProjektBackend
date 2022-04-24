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
    @Column(name = "name", nullable = false, length = 512,unique = true)
    private String name;
    @Column(name = "password" ,nullable = false, length = 512)
    private String password;
    @Column(name = "email", nullable = false, length = 512)
    private String email;
}
