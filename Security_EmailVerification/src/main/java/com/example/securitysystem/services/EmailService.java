package com.example.securitysystem.services;

/**
 * Service interface for sending emails.
 *
 * <p>Defines a method to send an email to a specified recipient.</p>
 */
public interface EmailService {

  /**
   * Sends an email to the specified recipient.
   *
   * @param to The recipient's email address.
   * @param email The content of the email to be sent.
   */
  void sendEmail(String to, String email);
}
