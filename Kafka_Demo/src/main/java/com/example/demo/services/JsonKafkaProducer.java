package com.example.demo.services;

import static java.lang.String.format;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

import com.example.demo.dtos.ProductMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Service class for producing JSON messages to a Kafka topic.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JsonKafkaProducer {

  @Value("${spring.kafka.topic-json.name}")
  private String jsonTopic;

  private final KafkaTemplate<String, String> template;

  /**
   * Sends a {@link ProductMessage} to the Kafka topic.
   *
   * @param productMessage the message to be sent to the Kafka topic
   */
  public void sendMessage(ProductMessage productMessage) {
    log.info(format("Message sent -> %s", productMessage.toString()));

    Message<ProductMessage> message = MessageBuilder
        .withPayload(productMessage)
        .setHeader(TOPIC, jsonTopic)
        .build();

    template.send(message);
  }
}
