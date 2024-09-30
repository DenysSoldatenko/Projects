package com.example.demo.services;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Unit tests for the KafkaProducer service.
 */
@ExtendWith(MockitoExtension.class)
class KafkaProducerTest {

  @Mock
  private KafkaTemplate<String, String> kafkaTemplate;

  @InjectMocks
  private KafkaProducer kafkaProducer;

  private final String topic = "test-topic";

  /**
   * Sets up the test environment by initializing the KafkaProducer.
   *
   * <p>Note: The {@code jsonTopic} field in {@link KafkaProducer} has private access.
   * To facilitate testing, it should be made accessible
   * (e.g., by changing its access modifier to package-private).
   */
  @BeforeEach
  void setUp() {
    // kafkaProducer.topic = "test-topic";
  }

  @Test
  void testSendMessage() {
    String message = "Hello, Kafka!";
    kafkaProducer.sendMessage(message);
    verify(kafkaTemplate).send(topic, message);
  }

  @Test
  void testSendMessageWithNull() {
    String message = null;
    kafkaProducer.sendMessage(message);
    verify(kafkaTemplate).send(topic, null);
  }
}
