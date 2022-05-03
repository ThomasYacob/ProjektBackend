package com.spring.backend.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(UserAnswerId.class)
public class UserAnswer {
    @Column(name = "answer",nullable = false)
    private String answer;
    @Column(name = "dateSubmited",nullable = false)
    private Date date;
    @Column(name = "typeOfAnswer",nullable = false)
    private String type;
    @OneToOne
    @Id
    @JoinColumn(name = "userID",nullable = false)
    private User user;
    @OneToOne
    @Id
    @JoinColumn(name = "scoreId",nullable = false)
    Scoreboard scoreboard;
    @OneToOne
    @Id
    @JoinColumn(name = "dailyId",nullable = true)
    Daily daily;
    @OneToOne
    @Id
    @JoinColumn(name = "monthlyId",nullable = true)
    Monthly monthly;
    @OneToOne
    @Id
    @JoinColumn(name = "weeklyId",nullable = true)
    Weekly weekly;
}
