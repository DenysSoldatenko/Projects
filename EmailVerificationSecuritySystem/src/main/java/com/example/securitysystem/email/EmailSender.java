package com.example.securitysystem.email;

/**
 * An interface for sending emails.
 */
public interface EmailSender {
  void send(String to, String email);
}