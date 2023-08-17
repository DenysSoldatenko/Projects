package com.example.jwtsecuritysystem.services;

import com.example.jwtsecuritysystem.models.User;
import java.util.List;

public interface UserService {

  User register(User user);

  List<User> getAll();

  User findByUsername(String username);

  User findById(Long id);

  void delete(Long id);
}