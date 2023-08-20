package com.example.jwtsecuritysystem.controllers;

import com.example.jwtsecuritysystem.dto.AuthenticationRequestDto;
import com.example.jwtsecuritysystem.models.User;
import com.example.jwtsecuritysystem.security.token.JwtTokenProvider;
import com.example.jwtsecuritysystem.services.UserService;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final JwtTokenProvider jwtTokenProvider;

  private final UserService userService;

  @PostMapping("login")
  public ResponseEntity<Map<Object, Object>> login(
      @RequestBody AuthenticationRequestDto requestDto
  ) {
    String username = requestDto.username();
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(username, requestDto.password())
    );
    User user = userService.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User with username: " + username + " not found");
    }

    String token = jwtTokenProvider.createToken(username, user.getRoles());

    Map<Object, Object> response = new HashMap<>();
    response.put("username", username);
    response.put("token", token);

    return ResponseEntity.ok(response);
  }
}