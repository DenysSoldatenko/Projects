package com.example.joblisting.services;

import com.example.joblisting.models.Post;
import org.bson.Document;
import java.util.List;

public interface SearchService {

  List<Post> searchPostsByText(String text);

  List<Post> searchPostsByPattern(String pattern);

  List<Document> getCountByExperienceLevel();
}
