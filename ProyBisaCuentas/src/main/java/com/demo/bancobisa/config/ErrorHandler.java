package com.demo.bancobisa.config;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.logging.Level;

@Log
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception exception) {
        log.log(Level.SEVERE, "[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "] >>> EXCEPTION CAUSE: {0}", exception);
        return new ResponseEntity(
                new ErrorResponse()
                        .setCode("800")
                        .setMessage(exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<Object> handleCustomException(CustomException exception, WebRequest request) {
        try {
            return new ResponseEntity(
                    new ErrorResponse()
                            .setCode("800")
                            .setMessage(exception.getMessage()),
                    exception.getHttpStatus());
        } catch (Exception e) {
            return handleAllExceptions(e);
        }
    }

}
