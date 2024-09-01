package com.example.securitysystem.services;

import com.example.securitysystem.entities.User;

/**
 * Service interface for managing users in the security system.
 *
 * <p>This interface defines methods for user sign-up, saving tokens, and enabling users.</p>
 */
public interface UserService {

  String signUp(User appUser);

  String saveToken(User user);

  void enableUser(String email);
}
