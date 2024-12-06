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

  /**
   * Initializes the mocks before each test case.
   * This method is executed before each test in the test class,
   * ensuring that mocks are set up correctly for the tests.
   */
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAuthenticateSuccess() throws Exception {
    AuthenticationRequest request = new AuthenticationRequest("john.doe@gmail.com", "password");

    mockMvc.perform(post("/api/v1/auth/authenticate")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"john.doe@gmail.com\", \"password\":\"password\"}"))
        .andExpect(status().isOk());
  }

  @Test
  public void testAuthenticateForbidden() throws Exception {
    AuthenticationRequest request = new AuthenticationRequest("user@example.com", "wrongPassword");

    when(authenticationService.authenticateAndGenerateToken(any(AuthenticationRequest.class)))
        .thenThrow(new RuntimeException("Invalid credentials"));

    mockMvc.perform(post("/api/v1/auth/authenticate")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"user@example.com\", \"password\":\"wrongPassword\"}"))
        .andExpect(status().isForbidden());
  }

  @Test
  public void testAuthenticateBadRequest() throws Exception {
    String invalidJson = "{invalidJson}";

    mockMvc.perform(post("/api/v1/auth/authenticate")
        .contentType(MediaType.APPLICATION_JSON)
        .content(invalidJson))
        .andExpect(status().isBadRequest());
  }
}
