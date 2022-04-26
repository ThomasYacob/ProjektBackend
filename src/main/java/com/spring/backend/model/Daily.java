package com.spring.backend.model;


import lombok.*;
import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Daily {
    @Id
    @GeneratedValue
    @Column(name = "dailyID")
    private int id;
    @Column(name = "question",nullable = false,length = 512)
    private String question;
    @Column(name = "answer", nullable = false,length = 512)
    private String answer;
    @Column(name = "dateToPublish",nullable = false)
    private Date date;
}
