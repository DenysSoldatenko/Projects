package com.example.emailservice.services;

import com.example.shareddomain.models.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Service that consumes order events from Kafka.
 */
@Slf4j
@Service
public class OrderConsumer {

  @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
  public void consume(OrderEvent event) {
    log.info("Received order event in email service: {}", event);
  }
}