package com.victor.fashionblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private HttpStatus status;

    public ResourceNotFoundException(HttpStatus status) {

        super();
        this.status = status;
    }

    public ResourceNotFoundException(String message) {

        super(message);
    }

    public ResourceNotFoundException(String message, HttpStatus status) {

        super(message);
        this.status = status;
    }

    public ResourceNotFoundException(String message, Throwable cause) {

        super(message, cause);
    }

    public HttpStatus getStatus(){
        return status;
    }
}
