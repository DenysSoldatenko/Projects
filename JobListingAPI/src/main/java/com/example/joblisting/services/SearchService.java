package com.example.joblisting.services;

import com.example.joblisting.models.Post;
import java.util.List;
import org.bson.Document;

public interface SearchService {

  List<Post> searchPostsByText(String text);

  List<Post> searchPostsByPattern(String pattern);

  List<Document> getCountByExperienceLevel();
}
