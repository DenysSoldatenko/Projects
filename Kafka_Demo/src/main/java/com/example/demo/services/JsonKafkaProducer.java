package com.example.demo.services;

import static java.lang.String.format;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

import com.example.demo.dtos.ProductMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Service class for producing messages to a Kafka topic.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JsonKafkaProducer {

  private final KafkaTemplate<String, String> template;

  public void sendMessage(ProductMessage productMessage) {
    log.info(format("Message sent -> %s", productMessage.toString()));

    Message<ProductMessage> message = MessageBuilder
      .withPayload(productMessage)
      .setHeader(TOPIC, "Demo")
      .build();

    template.send(message);
  }
}
