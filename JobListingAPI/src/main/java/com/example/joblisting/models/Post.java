package com.example.joblisting.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Represents a job listing post.
 */
@Data
@Document(collection = "posts")
public class Post {

  @Id
  @Schema(hidden = true)
  private String id;

  @Field("profile")
  @Schema(
      description = "The profile or job title of the post",
      example = "Software Engineer"
  )
  private String profile;

  @Field("description")
  @Schema(
      description = "Description of the job post",
      example = "Software engineer who can work on enterprise projects using Spring Boot and MongoDB"
  )
  private String description;

  @Field("experience")
  @Schema(
      description = "Number of years of experience required",
      example = "5"
  )
  private int experience;

  @Field("technologies")
  @Schema(
      description = "List of technologies required for the job post",
      example = "[\"Java\", \"Spring Boot\", \"Microservices\"]"
  )
  private String[] technologies;
}