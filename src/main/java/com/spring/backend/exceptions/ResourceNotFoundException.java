package com.spring.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class that throws an exception when
 * a resource in the API requested by the client is not found.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@ResponseStatus(value =  HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    /**
     * Creates a ResourceNotFoundException with a message.
     * @param message the message of the Exception.
     */
    public ResourceNotFoundException(String message){
        super(message);
    }

    /**
     * Creates a ResourceNotFoundException.
     */
    public ResourceNotFoundException(){
        super();
    }
}
