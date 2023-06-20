package com.example.joblisting.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Represents a job listing post.
 */
@Data
@Document(collection = "example")
public class Post {
  private String profile;

  @Field("desc")
  private String description;

  @Field("exp")
  private int experience;

  @Field("techs")
  private String[] technologies;
}