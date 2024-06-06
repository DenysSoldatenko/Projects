package com.example.securitysystem.services;

import com.example.securitysystem.dtos.RegistrationRequest;

public interface RegistrationService {

  String register(RegistrationRequest request);

  String confirmToken(String token);
}
