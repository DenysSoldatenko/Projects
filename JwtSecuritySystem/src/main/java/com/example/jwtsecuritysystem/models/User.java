package com.example.jwtsecuritysystem.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Represents a user entity.
 */
@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {


  @Schema(
    description = "The unique username of the user",
    example = "regularUser123@gmail.com"
  )
  @Column(name = "username")
  private String username;

  @Schema(
    description = "The first name of the user",
    example = "Jane"
  )
  @Column(name = "first_name")
  private String firstName;

  @Schema(
    description = "The last name of the user",
    example = "Doe"
  )
  @Column(name = "last_name")
  private String lastName;

  @Schema(
    description = "The email address of the user",
    example = "jane.doe@example.com"
  )
  @Column(name = "email")
  private String email;

  @Schema(
    description = "The password of the user",
    example = "password123"
  )
  @Column(name = "password")
  private String password;

  @Schema(hidden = true)
  @ToString.Exclude
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
  )
  private List<Role> roles;
}