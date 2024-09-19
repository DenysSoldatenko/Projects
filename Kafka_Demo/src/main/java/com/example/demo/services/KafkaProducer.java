package com.example.demo.services;

import static java.lang.String.format;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Service for producing messages to Kafka topics.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

  private KafkaTemplate<String, String> template;

  public void sendMessage(String message) {
    log.info(format("Message sent %s", message));
    template.send("Demo", message);
  }
}
