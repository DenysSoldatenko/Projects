package com.example.demo.services;

import static java.lang.String.format;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Service for producing messages to Kafka topics.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

  @Value("${spring.kafka.topic.name}")
  private String topic;

  private final KafkaTemplate<String, String> template;

  public void sendMessage(String message) {
    log.info(format("Message sent %s", message));
    template.send(topic, message);
  }
}
