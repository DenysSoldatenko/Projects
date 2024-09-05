package com.example.jwtsecuritysystem.models;

import static jakarta.persistence.EnumType.STRING;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * A base entity class representing common fields for other entities.
 */
@Data
@MappedSuperclass
public class BaseEntity {

  @Schema(hidden = true)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Schema(
      description = "Date when the entity was created",
      example = "2024-01-01T00:00:00Z"
  )
  @CreatedDate
  @Column(name = "created")
  private Date created;

  @Schema(
      description = "Date when the entity was last updated",
      example = "2024-01-02T00:00:00Z"
  )
  @LastModifiedDate
  @Column(name = "updated")
  private Date updated;

  @Schema(
      description = "Status of the entity",
      example = "ACTIVE"
  )
  @Enumerated(STRING)
  @Column(name = "status")
  private Status status;
}