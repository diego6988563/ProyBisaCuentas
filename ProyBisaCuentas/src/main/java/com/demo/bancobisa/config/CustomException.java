package com.demo.bancobisa.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Accessors(chain = true)
public class CustomException extends Exception {

    private String message;
    private HttpStatus httpStatus;

    public CustomException(String message) {
        super(message);
        this.message = message;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public CustomException(String message, HttpStatus httpStatus) {
        this.message    = message;
        this.httpStatus = httpStatus;
    }

    public CustomException(String message, HttpStatus httpStatus, Throwable throwable) {
        super(throwable);
        this.message    = message;
        this.httpStatus = httpStatus;
    }

    public CustomException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

    public CustomException(Throwable throwable) {
        super(throwable);
    }

}