package com.spring.backend.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Weekly {
    @Id
    @GeneratedValue
    @Column(name = "weeklyID")
    private Long id;
    @Column(name = "question",nullable = false,length = 512)
    private String question;
    @Column(name = "answer", nullable = false,length = 512)
    private String answer;
    @Column(name = "dateToPublish",nullable = false, unique = true)
    private Date date;
}
