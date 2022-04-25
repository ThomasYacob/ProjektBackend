package com.spring.backend.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class user_scoreBoard implements Serializable{
    @OneToOne
    @Id
    @JoinColumn(name = "userId")
    private User user;
    @OneToOne
    @Id
    @JoinColumn(name = "scoreId")
    private Scoreboard scoreboard;
}
