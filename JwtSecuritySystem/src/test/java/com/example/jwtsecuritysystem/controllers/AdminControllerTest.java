package com.example.jwtsecuritysystem.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import com.example.jwtsecuritysystem.dto.AdminDto;
import com.example.jwtsecuritysystem.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Unit tests for the AdminController class.
 */
@ExtendWith(SpringExtension.class)
class AdminControllerTest {

  @Mock
  private UserService userService;

  @Mock
  private ModelMapper modelMapper;

  private AdminController adminController;

  @BeforeEach
  public void setUp() {
    adminController = new AdminController(userService, modelMapper);
  }

  @Test
  void testGetUserByIdNotFound() {
    Long userId = 1L;
    when(userService.findById(userId)).thenReturn(null);

    ResponseEntity<AdminDto> response = adminController.getUserById(userId);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNull(response.getBody());
  }
}
