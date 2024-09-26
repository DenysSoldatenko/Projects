package com.example.demo.services;

import static java.lang.String.format;

import com.example.demo.dtos.ProductMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Service class for consuming JSON messages from Kafka.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JsonKafkaConsumer {

  @KafkaListener(topics = "Demo_json", groupId = "kafka_group")
  public void consume(ProductMessage productMessage) {
    log.info(format("JSON message received -> %s", productMessage.toString()));
  }
}
