package com.example.joblisting.controllers;

import com.example.joblisting.models.Post;
import com.example.joblisting.services.PostService;
import com.example.joblisting.services.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling job listing posts.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Tag(name = "Post Controller", description = "APIs for managing job listing posts")
public class PostController {

  private final PostService postService;
  private final SearchService searchService;

  @GetMapping("/hello")
  @Operation(summary = "Returns a hello world message")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved the hello world message")
  })
  public String hello() {
    return "Hello world!";
  }

  @PostMapping("/initialize")
  @Operation(summary = "Initialize the collection with data")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully initialized the collection with data"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public String initializeCollection() {
    return postService.initializeCollectionWithData();
  }

  @GetMapping
  @Operation(summary = "Get all job posts")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved all job posts"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public List<Post> getAllPosts() {
    return postService.getPosts();
  }

  @PostMapping
  @Operation(summary = "Add a new job post")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Successfully added a new job post"),
      @ApiResponse(responseCode = "400", description = "Bad request, invalid post data"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public Post addPost(@RequestBody Post post) {
    return postService.createPost(post);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update an existing job post")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully updated the job post"),
      @ApiResponse(responseCode = "404", description = "Job post not found"),
      @ApiResponse(responseCode = "400", description = "Bad request, invalid post data"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public Post updatePost(@RequestBody Post post) {
    return postService.editPost(post);
  }

  @GetMapping("/search")
  @Operation(summary = "Search for job posts by text")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved job posts by text"),
      @ApiResponse(responseCode = "400", description = "Bad request, invalid search text"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public List<Post> searchPostsByText(@RequestParam String text) {
    return searchService.searchPostsByText(text);
  }

  @GetMapping("/searchByPattern")
  @Operation(summary = "Search for job posts by pattern")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved job posts by pattern"),
      @ApiResponse(responseCode = "400", description = "Bad request, invalid search pattern"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public List<Post> searchPostsByPattern(@RequestParam String text) {
    return searchService.searchPostsByPattern(text);
  }

  @GetMapping("/countByExperience")
  @Operation(summary = "Get job post count by experience level")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved job post count by experience level"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public List<Document> getCountByExperience() {
    return searchService.getCountByExperienceLevel();
  }
}