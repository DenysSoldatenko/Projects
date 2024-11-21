package com.example.joblisting.services;

import com.example.joblisting.models.Post;
import java.util.List;
import org.bson.Document;

/**
 * Service interface for searching and retrieving job posts.
 * Provides methods for text-based search, pattern matching, and aggregating data.
 */
public interface SearchService {

  /**
   * Searches job posts by a given text.
   * The search is based on a full-text match, returning posts that contain the specified text.
   *
   * @param text the text to search for in job posts.
   * @return a list of {@link Post} objects that match the search criteria.
   */
  List<Post> searchPostsByText(String text);

  /**
   * Searches job posts by a specified pattern using regular expressions.
   * This method allows for more flexible search criteria, such as partial matches.
   *
   * @param pattern the pattern (usually a regular expression) to search for in job posts.
   * @return a list of {@link Post} objects that match the given pattern.
   */
  List<Post> searchPostsByPattern(String pattern);

  /**
   * Retrieves the count of job posts grouped by experience level.
   * This method aggregates the number of job posts based on different experience levels
   * (e.g., entry-level, mid-level, senior-level).
   *
   * @return a list of {@link Document} objects grouped by experience level.
   */
  List<Document> getCountByExperienceLevel();
}
