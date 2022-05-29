package com.spring.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class that throws an exception when
 * a refresh token is invalid.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class TokenRefreshException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a TokenRefreshException with the token and a message.
     * @param token the token.
     * @param message the message of the Exception.
     */
    public TokenRefreshException(String token, String message) {
        super(String.format("Failed for [%s]: %s", token, message));
    }
}