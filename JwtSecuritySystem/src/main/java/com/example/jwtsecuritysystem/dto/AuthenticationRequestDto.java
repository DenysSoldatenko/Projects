package com.example.jwtsecuritysystem.dto;

/**
 * Data Transfer Object (DTO) representing an authentication request.
 */
public record AuthenticationRequestDto(String username, String password) { }