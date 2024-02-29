package com.enviro.assessment.grad001.boitumelotshehla.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class exceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("handleEntityNotFoundException() - error, e = {}, message = {}, stackTrace = {}",
                e, e.getMessage(), e.getStackTrace());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(ValidationException.class)
    ResponseEntity<String> handleValidationException(ValidationException e) {
        log.error("handleValidationException() - error, e = {}, message = {}, stackTrace = {}",
                e, e.getMessage(), e.getStackTrace());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(BirthDateExtractionException.class)
    ResponseEntity<String> handleBirthDateExtractionException(BirthDateExtractionException e) {
        log.error("handleBirthDateExtractionException() - error, e = {}, message = {}, stackTrace = {}",
                e, e.getMessage(), e.getStackTrace());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(NotificationException.class)
    ResponseEntity<String> handleNotificationException(NotificationException e) {
        log.error("handleNotificationException() - error, e = {}, message = {}, stackTrace = {}",
                e, e.getMessage(), e.getStackTrace());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    @ExceptionHandler(CsvException.class)
    ResponseEntity<String> handleCsvException(CsvException e) {
        log.error("handleCsvException() - error, e = {}, message = {}, stackTrace = {}",
                e, e.getMessage(), e.getStackTrace());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(NoProductTypeException.class)
    ResponseEntity<String> NoProductTypeException(CsvException e) {
        log.error("handleNoProductTypeException() - error, e = {}, message = {}, stackTrace = {}",
                e, e.getMessage(), e.getStackTrace());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
