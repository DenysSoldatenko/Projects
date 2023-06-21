package com.example.joblisting.controllers;

import com.example.joblisting.models.Post;
import com.example.joblisting.repositories.PostRepository;
import com.example.joblisting.repositories.SearchRepository;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling job listing posts.
 */
@RestController
@RequestMapping(value = "/example")
public class PostController {

  private final PostRepository postRepository;

  private final SearchRepository searchRepository;

  @Autowired
  public PostController(PostRepository postRepository, SearchRepository searchRepository) {
    this.postRepository = postRepository;
    this.searchRepository = searchRepository;
  }

  @GetMapping
  public String hello() {
    return "Hello world!";
  }

  @GetMapping("/posts")
  public List<Post> getAllPosts() {
    return postRepository.findAll();
  }

  @PostMapping("/post")
  public Post addPost(@RequestBody Post post) {
    return postRepository.save(post);
  }

  @GetMapping("/posts/{text}")
  public List<Post> search(@PathVariable String text) {
    return searchRepository.findByText(text);
  }

  @GetMapping("/posts/searchByPattern/{text}")
  public List<Post> searchByPattern(@PathVariable String text) {
    return searchRepository.findByPattern(text);
  }

  @GetMapping("/posts/countByExp")
  public List<Document> countByExp() {
    return searchRepository.countByExperience();
  }
}