package com.spring.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints =
        {
        @UniqueConstraint(name = "UniqueAnswers", columnNames = {"user_id","typeOfQuestion","questionId"})})
public class UserAnswer {
    @Id
    @GeneratedValue
    @Column(name = "userAnswerid")
    private long id;
    @Column(name = "answer",nullable = false)
    private String answer;
    @Column(name = "dateSubmited",nullable = true)
    private Date date;
    @Enumerated(EnumType.STRING)
    private typeOfQuestion typeOfQuestion;
    @Column(name = "questionId")
    private Long questionId;
    @Column(name = "corrected")
    private String corrected;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public UserAnswer(String answer, Date date, com.spring.backend.model.typeOfQuestion typeOfQuestion, Long questionId, User user) {
        this.answer = answer;
        this.date = date;
        this.typeOfQuestion = typeOfQuestion;
        this.questionId = questionId;
        this.user = user;
    }

    public UserAnswer(String answer, Date date, com.spring.backend.model.typeOfQuestion typeOfQuestion, Long questionId) {
        this.answer = answer;
        this.date = date;
        this.typeOfQuestion = typeOfQuestion;
        this.questionId = questionId;
    }
}
