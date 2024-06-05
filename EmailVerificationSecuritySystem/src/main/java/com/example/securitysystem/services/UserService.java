package com.example.securitysystem.services;

import com.example.securitysystem.entities.User;

public interface UserService {
  String signUp(User appUser);

  String saveToken(User user);

  void enableUser(String email);
}
