package com.example.demo.controllers;

import static org.springframework.http.ResponseEntity.ok;

import com.example.demo.dtos.ProductMessage;
import com.example.demo.services.JsonKafkaProducer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling Kafka JSON message publishing requests.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kafka")
@Tag(
    name = "Json Message Controller",
    description = "Operations for publishing JSON messages to Kafka"
)
public class JsonMessageController {

  private final JsonKafkaProducer producer;

  /**
   * Publishes a JSON message to Kafka containing product information.
   *
   * @param productMessage The product information message to be sent to Kafka.
   * @return A ResponseEntity with a success message.
   */
  @Operation(summary = "Publish JSON message",
      description = "Sends a JSON message containing product information to Kafka."
  )
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "JSON message successfully sent to Kafka"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @PostMapping("/publish")
  public ResponseEntity<String> publish(@RequestBody ProductMessage productMessage) {
    producer.sendMessage(productMessage);
    return ok("JSON message successfully sent to Kafka");
  }
}
