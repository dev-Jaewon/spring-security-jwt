package com.devjaewon.securityproject.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.devjaewon.securityproject.exceptions.exception.ConflictException;

@RestControllerAdvice
public class GlobalApiErrorHandler {

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<Map<String, String>> conflictException(Exception e) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;

        Map<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, httpStatus);
    }
}
