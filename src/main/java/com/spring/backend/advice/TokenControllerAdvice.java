package com.spring.backend.advice;

import com.spring.backend.exceptions.TokenRefreshException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

/**
 * This is a global exception class that handles the specific exception (TokenRefreshException)
 * an exception thrown when an issue occurs when acquiring a new access token.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@RestControllerAdvice
public class TokenControllerAdvice {

    /**
     * Creates a Token Refresh Exception with a Status code, Date, Message and Description.
     * @param ex the exception that occurred.
     * @param request the HTTP Request by the client.
     * @return an ErrorMessage.
     */
    @ExceptionHandler(value = TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }
}
