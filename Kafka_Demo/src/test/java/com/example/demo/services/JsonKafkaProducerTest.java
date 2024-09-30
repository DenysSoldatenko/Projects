package com.example.demo.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.example.demo.dtos.ProductMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;

/**
 * Unit tests for the {@link JsonKafkaProducer} class.
 */
@ExtendWith(MockitoExtension.class)
class JsonKafkaProducerTest {

  @Mock
  private KafkaTemplate<String, String> kafkaTemplate;

  @InjectMocks
  private JsonKafkaProducer jsonKafkaProducer;

  /**
   * Sets up the test environment by initializing the JsonKafkaProducer.
   *
   * <p>Note: The {@code jsonTopic} field in {@link JsonKafkaProducer} has private access.
   * To facilitate testing, it should be made accessible
   * (e.g., by changing its access modifier to package-private).
   */
  @BeforeEach
  void setUp() {
    // jsonKafkaProducer.jsonTopic = "test-topic";
  }

  @Test
  void testSendMessage() {
    ProductMessage productMessage = new ProductMessage("1", "Test Product", 10.99, true);
    jsonKafkaProducer.sendMessage(productMessage);
    verify(kafkaTemplate).send(any(Message.class));
  }
}
