package com.example.joblisting.services;

import com.example.joblisting.models.Post;
import java.util.List;
import org.bson.Document;

/**
 * Service interface for searching and retrieving job posts.
 * Provides methods for text-based search, pattern matching, and aggregating data.
 */
public interface SearchService {

  List<Post> searchPostsByText(String text);

  List<Post> searchPostsByPattern(String pattern);

  List<Document> getCountByExperienceLevel();
}
