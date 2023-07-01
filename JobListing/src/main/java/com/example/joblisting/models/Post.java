package com.example.joblisting.models;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Represents a job listing post.
 */
@Data
@Document(collection = "example")
public class Post {
  @Id
  private String id;

  private String profile;

  @Field("desc")
  private String description;

  @Field("exp")
  private int experience;

  @Field("techs")
  private String[] technologies;
}