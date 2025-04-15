package com.sandbox.server.controller;

import com.sandbox.server.exception.BasicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExnHandler {

    @ExceptionHandler(Exception.class)
    private ResponseEntity<String> genericError(Exception e) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Undefined Error");

        log.error("Undefined error: {}", e.getMessage(), e);

        return new ResponseEntity<>("Undefined Error", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BasicException.class)
    private ResponseEntity<String> genericError(BasicException e) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Generic Error");

        log.error("Undefined error: {}", e.getMessage(), e);

        return new ResponseEntity<>("Generic Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
