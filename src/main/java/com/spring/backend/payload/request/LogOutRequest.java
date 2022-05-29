package com.spring.backend.payload.request;

/**
 * A representation of the logout HTTP request provided by the Client.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
public class LogOutRequest {
    private Long userId;

    public Long getUserId() {
        return this.userId;
    }
}
