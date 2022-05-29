package com.spring.backend.payload.request;

import javax.validation.constraints.NotBlank;

/**
 * A representation of the login HTTP request provided by the Client.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
public class LoginRequest {
//    @NotBlank
    private String username;

//    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
