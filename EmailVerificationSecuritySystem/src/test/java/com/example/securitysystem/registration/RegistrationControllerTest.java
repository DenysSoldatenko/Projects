package com.example.securitysystem.registration;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Unit tests for the RegistrationController class.
 */
@ExtendWith(SpringExtension.class)
public class RegistrationControllerTest {

  @Mock
  private RegistrationService registrationService;

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    RegistrationController registrationController = new RegistrationController(registrationService);
    mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
  }

  @Test
  public void testRegister() throws Exception {
    RegistrationRequest request
        = new RegistrationRequest("John", "Doe", "john@example.com", "password123");

    when(registrationService.register(request)).thenReturn("Registration successful");

    mockMvc.perform(post("/api/v1/registration")
    .contentType("application/json")
    .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@example.com\","
        + "\"password\":\"password123\"}"))
        .andExpect(status().isOk())
        .andExpect(content().string("Registration successful"));
  }

  @Test
  public void testConfirm() throws Exception {
    String token = "token123";

    when(registrationService.confirmToken(token)).thenReturn("Confirmation successful");

    mockMvc.perform(get("/api/v1/registration/confirm")
    .param("token", token))
    .andExpect(status().isOk())
        .andExpect(content().string("Confirmation successful"));
  }

  @Test
  public void testIndex() throws Exception {
    mockMvc.perform(get("/api/v1/registration/index"))
    .andExpect(status().isOk())
        .andExpect(content().string("Hello user!"));
  }
}
