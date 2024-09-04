package com.example.springsecuritysystem.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.springsecuritysystem.dtos.AuthenticationRequest;
import com.example.springsecuritysystem.services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Unit tests for the AuthenticationController class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private AuthenticationService authenticationService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAuthenticate_Success() throws Exception {
    AuthenticationRequest request = new AuthenticationRequest("john.doe@gmail.com", "password");

    mockMvc.perform(post("/api/v1/auth/authenticate")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"john.doe@gmail.com\", \"password\":\"password\"}"))
        .andExpect(status().isOk());
  }

  @Test
  public void testAuthenticate_Forbidden() throws Exception {
    AuthenticationRequest request = new AuthenticationRequest("user@example.com", "wrongPassword");

    when(authenticationService.authenticateAndGenerateToken(any(AuthenticationRequest.class)))
        .thenThrow(new RuntimeException("Invalid credentials"));

    mockMvc.perform(post("/api/v1/auth/authenticate")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"user@example.com\", \"password\":\"wrongPassword\"}"))
        .andExpect(status().isForbidden());
  }

  @Test
  public void testAuthenticate_BadRequest() throws Exception {
    String invalidJson = "{invalidJson}";

    mockMvc.perform(post("/api/v1/auth/authenticate")
        .contentType(MediaType.APPLICATION_JSON)
        .content(invalidJson))
        .andExpect(status().isBadRequest());
  }
}
