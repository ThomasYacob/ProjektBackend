package com.spring.backend.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserAnswer {
    @Id
    @GeneratedValue
    @Column(name = "userAnswerid")
    private long id;
    @Column(name = "answer",nullable = false)
    private String answer;
    @Column(name = "dateSubmited",nullable = false)
    private Date date;
    @Enumerated(EnumType.STRING)
    private typeOfQuestion typeOfQuestion;
    @Column(name = "questionId")
    private int questionId;
    @ManyToOne
    @ToString.Exclude
    private User user;

    public UserAnswer(String answer, Date date, com.spring.backend.model.typeOfQuestion typeOfQuestion, int questionId, User user) {
        this.answer = answer;
        this.date = date;
        this.typeOfQuestion = typeOfQuestion;
        this.questionId = questionId;
        this.user = user;
    }

    public UserAnswer(String answer, Date date, com.spring.backend.model.typeOfQuestion typeOfQuestion, int questionId) {
        this.answer = answer;
        this.date = date;
        this.typeOfQuestion = typeOfQuestion;
        this.questionId = questionId;
    }
}
