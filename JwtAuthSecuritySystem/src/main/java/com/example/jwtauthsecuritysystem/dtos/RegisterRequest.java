package com.example.jwtauthsecuritysystem.dtos;

public record RegisterRequest(String firstname, String lastname,
                              String email, String password) {
}