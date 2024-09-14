package com.example.demo.initializers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for the StudentInitializer class.
 */
@ExtendWith(MockitoExtension.class)
class StudentInitializerTest {

  @Mock
  private StudentRepository studentRepository;

  @InjectMocks
  private StudentInitializer studentInitializer;

  @Test
  void shouldInitializeDatabaseWithSampleData() {
    when(studentRepository.insert(any(Student.class))).thenAnswer(invocation -> invocation.getArgument(0));

    String result = studentInitializer.initDatabase();

    verify(studentRepository, times(10)).insert(any(Student.class));
    assertEquals("Sample database initialized with 10 random records.", result);
  }
}
