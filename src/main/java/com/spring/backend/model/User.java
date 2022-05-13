package com.spring.backend.model;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "email", nullable = false, length = 512,unique = true)
    private String email;
    @Column(name = "username", nullable = false, length = 512,unique = true)
    private String username;
    @Column(name = "password" ,nullable = false, length = 512)
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<UserAnswer> userAnswers = new HashSet<>();
}
