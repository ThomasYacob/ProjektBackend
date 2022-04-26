package com.spring.backend.model;

import java.io.Serializable;

public class UserweeklyscoreId implements Serializable {
    private User user;
    private Scoreboard scoreboard;
    private Daily daily;

    public UserweeklyscoreId(User user, Scoreboard scoreboard, Daily daily) {
        this.user = user;
        this.scoreboard = scoreboard;
        this.daily = daily;
    }

    public UserweeklyscoreId() {
    }
}
