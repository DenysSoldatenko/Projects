package com.example.joblisting.services.impl;

import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Filters.regex;
import static java.util.Arrays.asList;

import com.example.joblisting.models.Post;
import com.example.joblisting.services.SearchService;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

  private final MongoConverter converter;
  private final MongoClient client;

  @Override
  public List<Post> searchPostsByText(String text) {

    final List<Post> posts = new ArrayList<>();

    MongoDatabase database = client.getDatabase("jobs");
    MongoCollection<Document> collection = database.getCollection("example");

    AggregateIterable<Document> result = collection.aggregate(
        asList(
            new Document(
              "$search", new Document(
                  "text",
                  new Document("query", text).append("path", asList("techs", "desc", "profile")))
            ),
            new Document("$sort", new Document("exp", 1L)),
            new Document("$limit", 5L))
        );

    result.forEach(doc -> posts.add(converter.read(Post.class, doc)));

    return posts;
  }

  @Override
  public List<Post> searchPostsByPattern(String pattern) {
    final List<Post> posts = new ArrayList<>();

    MongoDatabase database = client.getDatabase("jobs");
    MongoCollection<Document> collection = database.getCollection("example");

    Bson filter = or(
        regex("desc", ".*" + pattern + ".*", "i"),
        regex("profile", ".*" + pattern + ".*", "i"),
        regex("techs", ".*" + pattern + ".*", "i")
    );

    List<Document> documents = collection.find(filter)
        .sort(new Document("exp", -1))
        .into(new ArrayList<>());

    documents.forEach(doc -> posts.add(converter.read(Post.class, doc)));

    return posts;
  }

  @Override
  public List<Document> getCountByExperienceLevel() {
    final List<Document> posts = new ArrayList<>();

    MongoDatabase database = client.getDatabase("jobs");
    MongoCollection<Document> collection = database.getCollection("example");

    AggregateIterable<Document> result = collection.aggregate(asList(
          new Document("$group",
          new Document("_id", "$exp").append("count", new Document("$sum", 1L))),
          new Document("$project",
          new Document("_id", 0).append("exp", "$_id").append("count", 1)),
          new Document("$sort",
          new Document("count", -1L)))
    );

    result.forEach(doc -> posts.add(converter.read(Document.class, doc)));

    return posts;
  }
}