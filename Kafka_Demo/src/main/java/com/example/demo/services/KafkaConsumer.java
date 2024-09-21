package com.example.demo.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * Service class for consuming messages from Kafka.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

  @KafkaListener(topics = "Demo", groupId = "kafka_group")
  public void consume(String message) {
    log.info("Message received -> {}", message);
  }
}
