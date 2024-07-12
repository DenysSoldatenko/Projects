package com.example.joblisting.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.example.joblisting.models.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.convert.MongoConverter;

class SearchServiceImplTest {

  @Mock
  private MongoConverter converter;

  @Mock
  private MongoClient client;

  @Mock
  private MongoDatabase database;

  @Mock
  private MongoCollection<Document> collection;

  @Mock
  private FindIterable<Document> findIterable;

  @Mock
  private AggregateIterable<Document> aggregateIterable;

  @InjectMocks
  private SearchServiceImpl searchService;

  @BeforeEach
  void setUp() {
    openMocks(this);
    when(client.getDatabase("mongo_project")).thenReturn(database);
    when(database.getCollection("posts")).thenReturn(collection);
  }

  @Test
  void searchPostsByText() {
    String searchText = "developer";
    Document postDoc = new Document("description", "Java developer")
        .append("profile", "Software engineer")
        .append("technologies", "Java, Spring");

    when(collection.find(any(Bson.class))).thenReturn(findIterable);
    when(findIterable.sort(any(Document.class))).thenReturn(findIterable);
    when(findIterable.limit(anyInt())).thenReturn(findIterable);
    when(findIterable.into(anyList())).thenReturn(List.of(postDoc));
    when(converter.read(Post.class, postDoc)).thenReturn(new Post());

    List<Post> posts = searchService.searchPostsByText(searchText);

    assertNotNull(posts);
    assertEquals(1, posts.size());

    verify(collection).createIndex(any(Document.class));
  }

  @Test
  void searchPostsByPattern() {
    String pattern = "developer";
    Document postDoc = new Document("description", "Java developer")
        .append("profile", "Software engineer")
        .append("technologies", "Java, Spring");

    when(collection.find(any(Bson.class))).thenReturn(findIterable);
    when(findIterable.sort(any(Document.class))).thenReturn(findIterable);
    when(findIterable.into(anyList())).thenReturn(List.of(postDoc));
    when(converter.read(Post.class, postDoc)).thenReturn(new Post());

    List<Post> posts = searchService.searchPostsByPattern(pattern);

    assertNotNull(posts);
    assertEquals(1, posts.size());
  }

  @Test
  void getCountByExperienceLevel() {
    Document countDoc = new Document("_id", "Junior").append("count", 5);

    when(collection.aggregate(anyList())).thenReturn(aggregateIterable);
    when(aggregateIterable.into(anyList())).thenReturn(List.of(countDoc));
    when(converter.read(Document.class, countDoc)).thenReturn(countDoc);

    List<Document> counts = searchService.getCountByExperienceLevel();

    assertNotNull(counts);
  }
}