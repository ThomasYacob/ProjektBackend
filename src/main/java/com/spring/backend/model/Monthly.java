package com.spring.backend.model;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Monthly {
    @Id
    @GeneratedValue
    @Column(name = "monthlyID")
    private int id;
    @Column(name = "question",nullable = false,length = 512)
    private String question;
    @Column(name = "answer", nullable = false,length = 512)
    private String answer;
    @Column(name = "hint1",nullable = false,length = 512)
    private String hint1;
    @Column(name = "hint2",nullable = false,length = 512)
    private String hint2;
    @Column(name = "hint3",nullable = false,length = 512)
    private String hint3;
    @Column(name = "dateToPublish",nullable = true)
    private Date date;


}
