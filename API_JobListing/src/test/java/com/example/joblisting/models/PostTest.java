package com.example.joblisting.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Post class.
 */
class PostTest {

  @Test
  void testGettersAndSetters() {
    Post post = new Post();

    post.setId("123");
    post.setProfile("Software Engineer");
    post.setDescription("Job description here");
    post.setExperience(3);
    post.setTechnologies(new String[]{"Java", "Spring Boot"});

    assertEquals("123", post.getId());
    assertEquals("Software Engineer", post.getProfile());
    assertEquals("Job description here", post.getDescription());
    assertEquals(3, post.getExperience());
    assertEquals("Java", post.getTechnologies()[0]);
    assertEquals("Spring Boot", post.getTechnologies()[1]);
  }
}
