package com.dz.itinfinity.backend_payment.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler (ExceptionFailedCreateAPI.class)
    public ResponseEntity<?> resourceNotFoundHandling(ExceptionFailedCreateAPI exception){
        return new ResponseEntity<>("error ....", HttpStatus.EXPECTATION_FAILED);
    }
}
