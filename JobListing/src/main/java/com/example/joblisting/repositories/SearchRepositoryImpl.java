package com.example.joblisting.repositories;

import com.example.joblisting.models.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

/**
 * Implementation of the SearchRepository interface for searching job posts.
 */
@Component
public class SearchRepositoryImpl implements SearchRepository {

  private final MongoConverter converter;
  private final MongoClient client;

  @Autowired
  public SearchRepositoryImpl(MongoConverter converter, MongoClient client) {
    this.converter = converter;
    this.client = client;
  }

  @Override
  public List<Post> findByText(String text) {

    final List<Post> posts = new ArrayList<>();

    MongoDatabase database = client.getDatabase("jobs");
    MongoCollection<Document> collection = database.getCollection("example");

    AggregateIterable<Document> result = collection.aggregate(
        Arrays.asList(
            new Document("$search", new Document("text",
                new Document("query", text)
                    .append("path", Arrays.asList("techs", "desc", "profile")))),
            new Document("$sort", new Document("exp", 1L)),
            new Document("$limit", 5L))
        );

    result.forEach(doc -> posts.add(converter.read(Post.class, doc)));

    return posts;
  }

  @Override
  public List<Post> findByPattern(String pattern) {
    final List<Post> posts = new ArrayList<>();

    MongoDatabase database = client.getDatabase("jobs");
    MongoCollection<Document> collection = database.getCollection("example");

    Bson filter = Filters.or(
        Filters.regex("desc", ".*" + pattern + ".*", "i"),
        Filters.regex("profile", ".*" + pattern + ".*", "i"),
        Filters.regex("techs", ".*" + pattern + ".*", "i")
    );

    List<Document> documents = collection.find(filter)
        .sort(new Document("exp", -1))
        .into(new ArrayList<>());

    documents.forEach(doc -> posts.add(converter.read(Post.class, doc)));

    return posts;
  }

  @Override
  public List<Document> countByExperience() {
    final List<Document> posts = new ArrayList<>();

    MongoDatabase database = client.getDatabase("jobs");
    MongoCollection<Document> collection = database.getCollection("example");

    AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
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