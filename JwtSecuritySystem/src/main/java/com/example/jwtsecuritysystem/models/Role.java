package com.example.jwtsecuritysystem.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents a role entity for user permissions.
 */
@Data
@Entity
@Table(name = "roles")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Represents a role entity for user permissions")
public class Role extends BaseEntity {

  @Schema(
    description = "Name of the role",
    example = "ROLE_USER"
  )
  @Column(name = "name")
  private String name;

  @Schema(hidden = true)
  @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
  private List<User> users;
}