package com.spring.backend.model;

import java.io.Serializable;

public class UserAnswerId implements Serializable {
    private User user;
    private Scoreboard scoreboard;
    private Daily daily;
    private Monthly monthly;
    private Weekly weekly;

    public UserAnswerId(){
    }

    public UserAnswerId(User user, Scoreboard scoreboard, Daily daily, Monthly monthly, Weekly weekly) {
        this.user = user;
        this.scoreboard = scoreboard;
        this.daily = daily;
        this.monthly = monthly;
        this.weekly = weekly;
    }

    public UserAnswerId(User user, Scoreboard scoreboard, Daily daily) {
        this.user = user;
        this.scoreboard = scoreboard;
        this.daily = daily;
        this.weekly = null;
        this.monthly = null;
    }

    public UserAnswerId(User user, Scoreboard scoreboard, Monthly monthly) {
        this.user = user;
        this.scoreboard = scoreboard;
        this.daily = null;
        this.weekly = null;
        this.monthly = monthly;
    }

    public UserAnswerId(User user, Scoreboard scoreboard, Weekly weekly) {
        this.user = user;
        this.scoreboard = scoreboard;
        this.daily = null;
        this.weekly = weekly;
        this.monthly = null;
    }




}
