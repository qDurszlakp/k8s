package com.sandbox.server.kafka;

import com.sandbox.util.kafka.KafkaTopicInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FooLogConsumer {

    @KafkaListener(topics = "#{T(com.sandbox.util.kafka.KafkaTopicInfo).FOO_LOGGING.getTopicName()}",
                   groupId = "#{T(com.sandbox.util.kafka.KafkaTopicInfo).FOO_LOGGING.getConsumerGroupId()}")
    public void consumeFooLog(String message) {
        log.info("[Server] Received message from topic {}: {}", KafkaTopicInfo.FOO_LOGGING.getTopicName(), message);
    }
} 