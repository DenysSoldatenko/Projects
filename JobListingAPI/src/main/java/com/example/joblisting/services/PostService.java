package com.example.joblisting.services;

import com.example.joblisting.models.Post;
import java.util.List;

public interface PostService {

  List<Post> getPosts();

  Post createPost(Post post);

  Post editPost(Post post);

  String initializeCollectionWithData();
}
