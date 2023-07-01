package com.example.joblisting.repositories;

import com.example.joblisting.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing job listing posts in a MongoDB database.
 */
public interface PostRepository extends MongoRepository<Post, String> {

}