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

  /**
   * Consumes JSON messages from the Kafka topic.
   * This method listens for messages on the topic specified in the application configuration
   * and logs the received `ProductMessage` object.
   *
   * @param productMessage The deserialized ProductMessage object from the Kafka message.
   */
  @KafkaListener(
      topics = "${spring.kafka.topic-json.name}",
      groupId = "${spring.kafka.consumer.group-id}"
  )
  public void consume(ProductMessage productMessage) {
    log.info(format("JSON message received -> %s", productMessage.toString()));
  }
}
