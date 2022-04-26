package com.spring.backend.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(UsermonthlyscoreId.class)
public class Usermonthlyscore implements Serializable {
    @Column(name = "answer")
    private String answer;
    @Column(name = "dateSubmited")
    private Date date;
    @OneToOne
    @Id
    @JoinColumn(name = "userID")
    private User user;
    @OneToOne
    @Id
    @JoinColumn(name = "scoreId")
    Scoreboard scoreboard;
    @OneToOne
    @Id
    @JoinColumn(name = "monthlyId")
    Daily daily;
}
