package com.example.accountapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

/**
 * Represents an account entity.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Tag(name = "Account", description = "Operations related to account management")
public class Account extends RepresentationModel<Account> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique identifier of the account", example = "1")
  private Integer id;

  @Column(nullable = false, unique = true, length = 20)
  @Schema(description = "Unique account number", example = "94667f86")
  private String accountNumber;

  @Schema(description = "Current balance of the account", example = "1000.50")
  private float balance;
}