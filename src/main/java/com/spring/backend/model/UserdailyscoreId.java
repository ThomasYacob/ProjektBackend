package com.spring.backend.model;


import java.io.Serializable;

public class UserdailyscoreId implements Serializable {

    private User user;
    private Scoreboard scoreboard;
    private Daily daily;

    public UserdailyscoreId(){

    }

    public UserdailyscoreId(User user, Scoreboard scoreboard, Daily daily) {
        this.user = user;
        this.scoreboard = scoreboard;
        this.daily = daily;
    }
}
