package com.spring.backend.payload.request;

import javax.validation.constraints.NotBlank;

/**
 * A representation of the Refresh Token HTTP request provided by the Client.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
