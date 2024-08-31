package com.example.joblisting.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.joblisting.models.Post;
import com.example.joblisting.repositories.PostRepository;
import com.example.joblisting.services.impl.PostServiceImpl;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Collections;
import java.util.List;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PostServiceImplTest {

  @Mock
  private MongoClient client;

  @Mock
  private PostRepository postRepository;

  @Mock
  private MongoDatabase database;

  @Mock
  private MongoCollection<Document> collection;

  @InjectMocks
  private PostServiceImpl postService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    when(client.getDatabase("mongo_project")).thenReturn(database);
    when(database.getCollection("posts")).thenReturn(collection);
  }

  @Test
  void getPosts() {
    Post post = new Post();
    when(postRepository.findAll()).thenReturn(Collections.singletonList(post));

    List<Post> posts = postService.getPosts();

    assertNotNull(posts);
    assertEquals(1, posts.size());
    assertEquals(post, posts.get(0));
  }

  @Test
  void createPost() {
    Post post = new Post();
    when(postRepository.save(any(Post.class))).thenReturn(post);

    Post createdPost = postService.createPost(post);

    assertNotNull(createdPost);
    assertEquals(post, createdPost);
  }

  @Test
  void editPost() {
    Post post = new Post();
    when(postRepository.save(any(Post.class))).thenReturn(post);

    Post updatedPost = postService.editPost(post);

    assertNotNull(updatedPost);
    assertEquals(post, updatedPost);
  }
}