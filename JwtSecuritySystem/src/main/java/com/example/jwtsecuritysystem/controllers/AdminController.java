package com.example.jwtsecuritysystem.controllers;

import com.example.jwtsecuritysystem.dto.AdminDto;
import com.example.jwtsecuritysystem.models.User;
import com.example.jwtsecuritysystem.services.UserService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/admin/")
public class AdminController {

  private final UserService userService;
  public final ModelMapper modelMapper;

  @GetMapping(value = "users/{id}")
  public ResponseEntity<AdminDto> getUserById(@PathVariable(name = "id") Long id) {
    User user = userService.findById(id);

    return Optional.ofNullable(user)
    .map(u -> new ResponseEntity<>(modelMapper.map(u, AdminDto.class), HttpStatus.OK))
    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
  }
}