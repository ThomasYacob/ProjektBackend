package com.spring.backend.advice;

import java.util.Date;

/**
 * This class creates a custom error message.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
public class ErrorMessage {

    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;

    /**
     * Creates an ErrorMessage with information.
     * @param statusCode the Status code that issued the error.
     * @param timestamp the Date and time of when the error occurred.
     * @param message the error message associated with the exception.
     * @param description the description of the error.
     */
    public ErrorMessage(int statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    /**
     * Get the Status code of the error.
     * @return the Status code.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Get the Time stamp of the error.
     * @return the Time stamp.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Get the Message of the error.
     * @return the Message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get the Description of the error.
     * @return the Description.
     */
    public String getDescription() {
        return description;
    }
}
