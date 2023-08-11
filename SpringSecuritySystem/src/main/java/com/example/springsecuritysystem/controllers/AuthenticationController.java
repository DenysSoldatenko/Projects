package com.example.springsecuritysystem.controllers;

import com.example.springsecuritysystem.configurations.JwtUtil;
import com.example.springsecuritysystem.dtos.AuthenticationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/auth")
@AllArgsConstructor
public class AuthenticationController {
  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final JwtUtil jwtUtil;

  @PostMapping("/authenticate")
  public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
    UserDetails user = userDetailsService.loadUserByUsername(request.email());

    if (user != null) {
      return ResponseEntity.ok(jwtUtil.generateToken(user));
    }
    return ResponseEntity.status(400).body("Some error has occurred!");
  }
}
