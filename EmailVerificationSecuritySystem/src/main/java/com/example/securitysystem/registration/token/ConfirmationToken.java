package com.example.securitysystem.registration.token;

import com.example.securitysystem.appuser.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a confirmation token associated with a user registration.
 */
@Data
@NoArgsConstructor
@Entity
public class ConfirmationToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String token;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime expiresAt;

  private LocalDateTime confirmedAt;

  @ManyToOne
  @JoinColumn(nullable = false, name = "app_user_id")
  private AppUser appUser;

  /**
   * Constructs a new ConfirmationToken with the specified details.
   *
   * @param token      the token string
   * @param createdAt  the date and time when the token was created
   * @param expiresAt  the date and time when the token expires
   * @param appUser    the user associated with this token
   */
  public ConfirmationToken(String token, LocalDateTime createdAt,
                           LocalDateTime expiresAt, AppUser appUser) {
    this.token = token;
    this.createdAt = createdAt;
    this.expiresAt = expiresAt;
    this.appUser = appUser;
  }
}