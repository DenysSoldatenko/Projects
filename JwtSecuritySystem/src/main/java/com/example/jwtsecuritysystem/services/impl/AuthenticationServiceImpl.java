package com.example.jwtsecuritysystem.services.impl;

import com.example.jwtsecuritysystem.dto.AuthenticationRequestDto;
import com.example.jwtsecuritysystem.models.User;
import com.example.jwtsecuritysystem.security.token.JwtTokenProvider;
import com.example.jwtsecuritysystem.services.AuthenticationService;
import com.example.jwtsecuritysystem.services.UserService;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserService userService;

  @Override
  public Map<Object, Object> login(AuthenticationRequestDto requestDto) {
    String username = requestDto.username();

    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(username, requestDto.password())
    );

    User user = userService.getByUsername(username);
    String token = jwtTokenProvider.createToken(username, user.getRoles());

    Map<Object, Object> response = new HashMap<>();
    response.put("username", username);
    response.put("token", token);

    return response;
  }
}
