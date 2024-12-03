package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.dtos.ProductMessage;
import com.example.demo.services.JsonKafkaProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Tests for the {@link JsonMessageController} class.
 */
public class JsonMessageControllerTest {

  private MockMvc mockMvc;

  @Mock
  private JsonKafkaProducer producer;

  @InjectMocks
  private JsonMessageController jsonMessageController;

  private ObjectMapper objectMapper;

  /**
   * Sets up the test environment before each test case.
   */
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(jsonMessageController).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void testPublishSuccess() throws Exception {
    ProductMessage productMessage = new ProductMessage("1", "Test Product", 10.99, true);
    String jsonMessage = objectMapper.writeValueAsString(productMessage);

    mockMvc.perform(post("/api/v1/kafka/publish")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMessage))
        .andExpect(status().isOk())
        .andExpect(content().string("JSON message successfully sent to Kafka"));

    ArgumentCaptor<ProductMessage> captor = ArgumentCaptor.forClass(ProductMessage.class);
    verify(producer).sendMessage(captor.capture());
    assertEquals(productMessage, captor.getValue());
  }
}
