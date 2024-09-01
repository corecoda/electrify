package com.corecoda.ikollect.exceptions;

import com.corecoda.ikollect.models.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle specific exception (e.g., Resource Not Found)
    @ExceptionHandler(iKollectNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(iKollectNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ResponseModel.failure(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // Handle other exceptions (e.g., Bad Request)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Invalid argument: " + ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationExceptionHandler.class)
    public ResponseEntity<ResponseModel> handleMethodArgumentNotValidException(ValidationExceptionHandler ex) {
        return new ResponseEntity<>(ResponseModel.failure(String.join("|",ex.getErrorMessages())), HttpStatus.BAD_REQUEST);
    }

    // Handle all other exceptions (Generic)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(ResponseModel.failure("An Unexpected error occured please try again or confirm current operation status")
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
