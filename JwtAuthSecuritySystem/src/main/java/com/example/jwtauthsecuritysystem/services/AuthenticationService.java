package com.example.jwtauthsecuritysystem.services;

import com.example.jwtauthsecuritysystem.configurations.jwt.JwtService;
import com.example.jwtauthsecuritysystem.dtos.AuthenticationRequest;
import com.example.jwtauthsecuritysystem.dtos.AuthenticationResponse;
import com.example.jwtauthsecuritysystem.dtos.RegisterRequest;
import com.example.jwtauthsecuritysystem.models.User;
import com.example.jwtauthsecuritysystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final UserFactory userFactory;

  public AuthenticationResponse registerUser(RegisterRequest request) {
    User user = userFactory.createUserFromRequest(request);
    userRepository.save(user);

    return createAuthenticationResponse(user);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticateUser(request);

    User user = userRepository.findByEmail(request.email())
        .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

    return createAuthenticationResponse(user);
  }

  private void authenticateUser(AuthenticationRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.email(), request.password())
    );
  }

  private AuthenticationResponse createAuthenticationResponse(User user) {
    String jwtToken = jwtService.generateToken(user);
    return new AuthenticationResponse(jwtToken);
  }
}
