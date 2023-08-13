package com.example.springsecuritysystem.dtos;

/**
 * Data Transfer Object (DTO) representing an authentication request.
 */
public record AuthenticationRequest(String email, String password) {
}
