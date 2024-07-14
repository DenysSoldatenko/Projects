package com.example.joblisting.services.impl;

import com.example.joblisting.models.Post;
import com.example.joblisting.repositories.PostRepository;
import com.example.joblisting.services.PostService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.InputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.bson.Document;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link PostService} interface.
 * Provides methods for managing job posts, including retrieval, creation, updating, and initializing data.
 */
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

  private final MongoClient client;
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

  @Override
  @SneakyThrows
  public String initializeCollectionWithData() {
    MongoDatabase database = client.getDatabase("mongo_project");
    MongoCollection<Document> collection = database.getCollection("posts");

    Resource resource = new ClassPathResource("data.json");
    InputStream inputStream = resource.getInputStream();

    ObjectMapper objectMapper = new ObjectMapper();
    List<Document> documents = objectMapper.readValue(inputStream, new TypeReference<>() {});

    collection.drop(); // Optional: drop collection if you want to start fresh
    collection.insertMany(documents);

    return "Data initialization successful!";
  }
}
