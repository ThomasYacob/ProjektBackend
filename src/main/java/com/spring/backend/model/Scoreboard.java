package com.spring.backend.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Scoreboard implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "scoreID")
    private int id;
    @Column(name = "dailyScore")
    private int dailyScore;
    @Column(name = "weeklyScore")
    private int weeklyScore;
    @Column(name = "monthlyScore")
    private int monthlyScore;

    @OneToOne
    @JoinColumn(name = "email",unique = true)
    private User user;

    public Scoreboard(User user) {
        this.user = user;
    }
}
