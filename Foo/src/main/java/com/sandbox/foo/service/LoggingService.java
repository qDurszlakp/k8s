package com.sandbox.foo.service;

import com.sandbox.util.kafka.KafkaTopicNames;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoggingService {

    private static final String KAFKA_TOPIC = KafkaTopicNames.FOO_LOGGING_TOPIC;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedRate = 10000)
    public void logMessage() {
        String randomUUID = UUID.randomUUID().toString();
        String message = "[Foo] Random log message: " + randomUUID;

        log.info("Generated message: {}", message);

        try {
            kafkaTemplate.send(KAFKA_TOPIC, message);
            log.debug("Message sent to Kafka topic {}: {}", KAFKA_TOPIC, message);
        } catch (Exception e) {
            log.error("Error sending message to Kafka topic {}: {}", KAFKA_TOPIC, e.getMessage(), e);
        }
    }
} 