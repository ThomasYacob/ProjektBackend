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
    private Long id;
    @Column(name = "question",nullable = false,length = 512)
    private String question;
    @Column(name = "alternative1", nullable = false,length = 512)
    private String alternative1;
    @Column(name = "alternative2", nullable = false,length = 512)
    private String alternative2;
    @Column(name = "alternative3", nullable = false,length = 512)
    private String alternative3;
    @Column(name = "rightAlternative", nullable = false)
    private int rightAlternative;
    @Column(name = "dateToPublish",nullable = false, unique = true)
    private Date date;
}

