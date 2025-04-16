package com.sandbox.foo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class LoggingService {

    @Scheduled(fixedRate = 10000)
    public void logMessage() {
        log.info("[Foo] Log message: {}", UUID.randomUUID().toString());
    }
} 