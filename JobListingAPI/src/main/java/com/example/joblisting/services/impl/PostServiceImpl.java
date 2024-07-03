package com.example.joblisting.services.impl;

import com.example.joblisting.models.Post;
import com.example.joblisting.repositories.PostRepository;
import com.example.joblisting.services.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

  @Override
  public List<Post> getPosts() {
    return postRepository.findAll();
  }

  @Override
  public Post createPost(Post post) {
    return postRepository.save(post);
  }

  @Override
  public Post editPost(Post post) {
    return postRepository.save(post);
  }
}
