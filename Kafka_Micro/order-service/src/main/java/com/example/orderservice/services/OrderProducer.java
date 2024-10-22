package com.example.orderservice.services;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

import com.example.shareddomain.models.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Service for producing order events to a Kafka topic.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProducer {

  private final NewTopic topic;
  private final KafkaTemplate<String, OrderEvent> template;

  /**
   * Sends an order event to the configured Kafka topic.
   *
   * @param event the order event to be sent
   */
  public void sendMessage(OrderEvent event) {
    log.info("Sending order event: {}", event);

    Message<OrderEvent> message = MessageBuilder
        .withPayload(event)
        .setHeader(TOPIC, topic.name())
        .build();

    template.send(message);
  }
}
