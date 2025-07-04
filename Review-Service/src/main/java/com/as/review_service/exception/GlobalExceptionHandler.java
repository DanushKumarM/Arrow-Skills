package com.as.review_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> handleHttpClientError(
            HttpClientErrorException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("External Service Error", ex.getMessage());
        return new ResponseEntity<>(error, ex.getStatusCode());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(
            Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

