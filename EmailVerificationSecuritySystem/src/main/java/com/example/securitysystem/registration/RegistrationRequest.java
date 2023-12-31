package com.example.securitysystem.registration;

/**
 * Represents a registration request for a new user.
 */
public record RegistrationRequest(String firstName, String lastName,
                                  String email, String password) {
}