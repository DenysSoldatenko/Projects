package com.example.joblisting.repositories;

import com.example.joblisting.models.Post;
import java.util.List;
import org.bson.Document;

/**
 * Repository interface for searching job listing posts.
 */
public interface SearchRepository {

  List<Post> findByText(String text);

  List<Post> findByPattern(String pattern);

  List<Document> countByExperience();
}