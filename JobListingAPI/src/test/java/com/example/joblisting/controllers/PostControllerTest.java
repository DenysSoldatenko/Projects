package com.example.joblisting.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.joblisting.models.Post;
import com.example.joblisting.repositories.PostRepository;
import com.example.joblisting.repositories.SearchRepository;
import java.util.Collections;
import java.util.List;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Unit tests for the PostController class.
 */
@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

  @InjectMocks
  private PostController postController;

  @Mock
  private PostRepository postRepository;

  @Mock
  private SearchRepository searchRepository;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
  }

  @Test
  public void testHello() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/example")
    .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetAllPosts() {
    List<Post> posts = Collections.singletonList(new Post());

    when(postRepository.findAll()).thenReturn(posts);

    List<Post> result = postController.getAllPosts();

    verify(postRepository, times(1)).findAll();
    assert result != null;
    assert result.size() == 1;
  }

  @Test
  public void testAddPost() {
    Post post = new Post();

    when(postRepository.save(any(Post.class))).thenReturn(post);

    Post result = postController.addPost(new Post());

    verify(postRepository, times(1)).save(any(Post.class));
    assert result != null;
  }

  @Test
  public void testUpdatePost() {
    Post post = new Post();

    when(postRepository.save(any(Post.class))).thenReturn(post);

    Post result = postController.updatePost(new Post());

    verify(postRepository, times(1)).save(any(Post.class));
    assert result != null;
  }

  @Test
  public void testSearch() {
    List<Post> posts = Collections.singletonList(new Post());

    when(searchRepository.findByText(anyString())).thenReturn(posts);

    List<Post> result = postController.search("text");

    verify(searchRepository, times(1)).findByText(anyString());
    assert result != null;
    assert result.size() == 1;
  }

  @Test
  public void testSearchByPattern() {
    List<Post> posts = Collections.singletonList(new Post());

    when(searchRepository.findByPattern(anyString())).thenReturn(posts);

    List<Post> result = postController.searchByPattern("pattern");

    verify(searchRepository, times(1)).findByPattern(anyString());
    assert result != null;
    assert result.size() == 1;
  }

  @Test
  public void testCountByExperience() {
    List<org.bson.Document> documents = Collections.singletonList(new org.bson.Document());

    when(searchRepository.findCountByExperience()).thenReturn(documents);

    List<Document> result = postController.countByExperience();

    verify(searchRepository, times(1)).findCountByExperience();
    assert result != null;
    assert result.size() == 1;
  }
}
