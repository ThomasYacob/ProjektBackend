package com.spring.backend.payload.response;

/**
 * A representation of a custom Message response sent from the Server to the Client.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
