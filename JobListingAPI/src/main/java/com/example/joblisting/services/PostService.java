package com.example.joblisting.services;

import com.example.joblisting.models.Post;
import java.util.List;

/**
 * Service interface for managing {@link Post} entities.
 * Provides methods for retrieving, creating, and updating job posts.
 */
public interface PostService {

  List<Post> getPosts();

  Post createPost(Post post);

  Post editPost(Post post);

  String initializeCollectionWithData();
}
