package org.example.may2024springhw.controllers;

import org.example.may2024springhw.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ErrorDTO> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorDTO
                                .builder()
                                .details(e.getMessage())
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String details = e
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField().concat(" : ").concat(error.getDefaultMessage()))
                .collect(Collectors.joining(" || "));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorDTO
                                .builder()
                                .details(details)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
}
