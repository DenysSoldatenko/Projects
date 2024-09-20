package com.example.demo.controllers;

import static org.springframework.http.ResponseEntity.ok;

import com.example.demo.dtos.MessageRequest;
import com.example.demo.services.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling Kafka message publishing requests.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kafka")
public class MessageController {

  private final KafkaProducer kafkaProducer;

  /**
   * Sends a message to the Kafka topic using the request body.
   *
   * @param messageRequest the request containing the message
   * @return ResponseEntity indicating the result of the operation
   */
  @PostMapping("/publish-via-body")
  public ResponseEntity<String> sendMessageFromBody(@RequestBody MessageRequest messageRequest) {
    String message = messageRequest.message();
    log.info("Received request to send message via body: {}", message);
    kafkaProducer.sendMessage(message);
    return ok("Message sent to the topic");
  }

  /**
   * Sends a message to the Kafka topic using a query parameter.
   *
   * @param message the message to be sent
   * @return ResponseEntity indicating the result of the operation
   */
  @GetMapping("/publish-via-query")
  public ResponseEntity<String> sendMessageFromQueryParam(@RequestParam("message") String message) {
    log.info("Received request to send message via query parameter: {}", message);
    kafkaProducer.sendMessage(message);
    return ok("Message sent to the topic");
  }
}
