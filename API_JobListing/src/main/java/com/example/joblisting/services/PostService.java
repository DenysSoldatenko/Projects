package com.example.joblisting.services;

import com.example.joblisting.models.Post;
import java.util.List;

/**
 * Service interface for managing {@link Post} entities.
 * Provides methods for retrieving, creating, updating, and initializing job posts.
 */
public interface PostService {

  /**
   * Retrieves all job posts.
   *
   * @return a list of all {@link Post} objects in the system.
   */
  List<Post> getPosts();

  /**
   * Creates a new job post.
   *
   * @param post the job post to be created.
   * @return the newly created {@link Post} object.
   */
  Post createPost(Post post);

  /**
   * Updates an existing job post.
   *
   * @param post the job post with updated information.
   * @return the updated {@link Post} object.
   */
  Post editPost(Post post);

  /**
   * Initializes the job post collection with sample data.
   *
   * @return a message indicating the result of the initialization.
   */
  String initializeCollectionWithData();
}
