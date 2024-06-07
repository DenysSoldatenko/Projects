package com.example.securitysystem.entities;

import static jakarta.persistence.EnumType.STRING;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Collections;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Represents an application user entity.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;

  private String lastName;

  private String email;

  private String password;

  @Enumerated(STRING)
  private UserRole userRole;

  private Boolean locked = false;

  private Boolean enabled = false;

  /**
   * Constructs a new AppUser with the specified user details.
   *
   * @param firstName   the first name of the user
   * @param lastName    the last name of the user
   * @param email       the email address of the user
   * @param password    the password of the user
   * @param userRole the role of the user (USER or ADMIN)
   */
  public User(String firstName, String lastName, String email,
                 String password, UserRole userRole) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.userRole = userRole;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
    return Collections.singletonList(authority);
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}