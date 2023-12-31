package com.example.joblisting.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.example.joblisting.models.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.convert.MongoConverter;

/**
 * Unit tests for the SearchRepositoryImpl class.
 */
class SearchRepositoryImplTest {

  @Mock
  private MongoClient mockClient;
  @Mock
  private MongoDatabase mockDatabase;
  @Mock
  private MongoCollection<Document> mockCollection;
  @Mock
  private MongoConverter mockConverter;

  private SearchRepository searchRepository;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    searchRepository = new SearchRepositoryImpl(mockConverter, mockClient);
  }

  @Test
  void testFindByText() {
    String searchText = "Java Developer";

    when(mockClient.getDatabase(any())).thenReturn(mockDatabase);
    when(mockDatabase.getCollection(any())).thenReturn(mockCollection);
    when(mockCollection.aggregate(any(List.class)))
        .thenReturn(Mockito.mock(AggregateIterable.class));
    when(mockConverter.read(eq(Post.class), any(Document.class))).thenReturn(new Post());

    List<Post> result = searchRepository.findByText(searchText);
    assertEquals(0, result.size());
  }

  @Test
  void testFindCountByExperience() {
    when(mockClient.getDatabase(any())).thenReturn(mockDatabase);
    when(mockDatabase.getCollection(any())).thenReturn(mockCollection);
    when(mockCollection.aggregate(any(List.class)))
        .thenReturn(Mockito.mock(AggregateIterable.class));
    when(mockConverter.read(eq(Document.class), any(Document.class))).thenReturn(new Document());

    List<Document> result = searchRepository.findCountByExperience();
    assertEquals(0, result.size());
  }
}
