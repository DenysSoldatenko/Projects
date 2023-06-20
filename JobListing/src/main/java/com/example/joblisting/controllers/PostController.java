package com.example.joblisting.controllers;

import com.example.joblisting.models.Post;
import com.example.joblisting.repositories.PostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

  @Autowired
  public PostController(PostRepository postRepository) {
    this.postRepository = postRepository;
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
}