package com.example.jwtsecuritysystem.services;

import com.example.jwtsecuritysystem.dto.AuthenticationRequestDto;
import java.util.Map;

/**
 * Service interface for handling authentication operations.
 */
public interface AuthenticationService {

  Map<Object, Object> login(AuthenticationRequestDto requestDto);
}
