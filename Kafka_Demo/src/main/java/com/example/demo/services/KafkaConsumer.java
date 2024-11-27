package com.example.demo.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Service class for consuming messages from Kafka.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

  /**
   * Consumes a message from the Kafka topic.
   * This method listens for messages from the Kafka topic, and logs the received message.
   *
   * @param message The message received from Kafka.
   */
  @KafkaListener(
      topics = "${spring.kafka.topic.name}",
      groupId = "${spring.kafka.consumer.group-id}"
  )
  public void consumeMessage(String message) {
    log.info("Message received -> {}", message);
  }
}
