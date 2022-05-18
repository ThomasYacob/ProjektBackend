package com.spring.backend.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User  {
    @Id
    @Column(name = "email", nullable = false, length = 512,unique = true)
    private String email;
    @Column(name = "username", nullable = false, length = 512,unique = true)
    private String username;
    @Column(name = "password" ,nullable = false, length = 512)
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",orphanRemoval = true)
    private List<UserAnswer> userAnswers = new ArrayList<>();


}
