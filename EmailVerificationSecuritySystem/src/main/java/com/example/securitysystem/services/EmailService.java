package com.example.securitysystem.services;

/**
 * Service interface for sending emails.
 *
 * <p>This interface defines a method for sending an email to a specified recipient.</p>
 */
public interface EmailService {

  void sendEmail(String to, String email);
}