package com.spring.backend.model;

import lombok.*;
import javax.persistence.*;

/**
 * A representation of the data for a Scoreboard in the system.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class  Scoreboard {
    @Id
    @GeneratedValue
    @Column(name = "scoreID")
    private Long id;
    @Column(name = "dailyScore")
    private int dailyScore;
    @Column(name = "weeklyScore")
    private int weeklyScore;
    @Column(name = "monthlyScore")
    private int monthlyScore;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    public Scoreboard(User user) {
        this.user = user;
    }
}
