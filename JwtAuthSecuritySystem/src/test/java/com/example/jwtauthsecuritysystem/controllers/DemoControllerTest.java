package com.example.jwtauthsecuritysystem.controllers;

import com.example.jwtauthsecuritysystem.controllers.DemoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(username = "testUser")
  void testSayHelloWithAuthenticatedUser() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/demo-controller/hello")
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(MockMvcResultMatchers.status().isOk())
    .andExpect(MockMvcResultMatchers.content().string("Hello from secured endpoint!"));
  }

  @Test
  void testSayHelloWithoutAuthenticatedUser() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/demo-controller/hello")
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(MockMvcResultMatchers.status().isForbidden());
  }
}
