package com.example.jwtauthsecuritysystem.dtos;

/**
 * Data Transfer Object (DTO) for user registration requests.
 */
public record RegisterRequest(String firstname, String lastname,
                              String email, String password) {
}