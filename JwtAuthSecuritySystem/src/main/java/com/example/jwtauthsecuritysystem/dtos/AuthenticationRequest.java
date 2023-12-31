package com.example.jwtauthsecuritysystem.dtos;

/**
 * Data Transfer Object (DTO) for authentication requests.
 */
public record AuthenticationRequest(String email, String password) {
}