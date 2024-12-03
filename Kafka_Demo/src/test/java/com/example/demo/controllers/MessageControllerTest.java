package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.dtos.MessageRequest;
import com.example.demo.services.KafkaProducer;
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
 * Tests for the {@link MessageController} class.
 */
public class MessageControllerTest {

  private MockMvc mockMvc;

  @Mock
  private KafkaProducer kafkaProducer;

  @InjectMocks
  private MessageController messageController;

  private ObjectMapper objectMapper;

  /**
   * Sets up the test environment before each test case.
   */
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void testSendMessageFromBodySuccess() throws Exception {
    MessageRequest messageRequest = new MessageRequest("Test message");
    String jsonMessage = objectMapper.writeValueAsString(messageRequest);

    mockMvc.perform(post("/api/v1/kafka/publish-via-body")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMessage))
        .andExpect(status().isOk())
        .andExpect(content().string("Message sent to the topic"));

    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    verify(kafkaProducer).sendMessage(captor.capture());
    assertEquals("Test message", captor.getValue());
  }

  @Test
  public void testSendMessageFromQueryParamSuccess() throws Exception {
    String message = "Test query message";

    mockMvc.perform(get("/api/v1/kafka/publish-via-query")
        .param("message", message))
        .andExpect(status().isOk())
        .andExpect(content().string("Message sent to the topic"));

    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    verify(kafkaProducer).sendMessage(captor.capture());
    assertEquals(message, captor.getValue());
  }
}
