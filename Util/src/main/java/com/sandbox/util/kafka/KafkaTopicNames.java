package com.sandbox.util.kafka;

/**
 * Utility class holding Kafka topic names used across the application.
 */
public final class KafkaTopicNames {

    public static final String FOO_LOGGING_TOPIC = "FOO_LOGGING_TOPIC";

    private KafkaTopicNames() {
        throw new IllegalStateException("Utility class");
    }
} 