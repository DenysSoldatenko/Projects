package com.example.springsecuritysystem.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Unit tests for the GreetingController class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testSayHelloForbidden() throws Exception {
    mockMvc.perform(get("/greetings")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isForbidden());
  }
}
