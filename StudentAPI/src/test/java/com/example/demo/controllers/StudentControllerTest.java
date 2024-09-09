package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.models.Student;
import com.example.demo.services.StudentService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

/**
 * Unit tests for the StudentController class.
 */
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

  @Mock
  private StudentService studentService;

  @InjectMocks
  private StudentController studentController;

  @Test
  void shouldInitializeData() {
    String expectedMessage = "Data initialized successfully";
    when(studentService.initializeData()).thenReturn(expectedMessage);

    ResponseEntity<String> result = studentController.initializeData();

    assertEquals(201, result.getStatusCodeValue());
    assertEquals(expectedMessage, result.getBody());
    verify(studentService, times(1)).initializeData();
  }

  @Test
  void shouldFetchAllStudents() {
    List<Student> students = List.of(
        Student.builder().id("123").firstName("John").lastName("Doe").build(),
        Student.builder().id("1234").firstName("Jane").lastName("Doe").build()
    );

    when(studentService.getAllStudents()).thenReturn(students);

    ResponseEntity<List<Student>> result = studentController.fetchAllStudents();

    assertEquals(200, result.getStatusCodeValue());
    assertEquals(2, result.getBody().size());
    verify(studentService, times(1)).getAllStudents();
  }

  @Test
  void shouldAddStudent() {
    Student student = Student.builder()
        .id("123")
        .firstName("John")
        .lastName("Doe")
        .build();

    when(studentService.addStudent(any(Student.class))).thenReturn(student);

    ResponseEntity<Student> result = studentController.addStudent(student);

    assertEquals(201, result.getStatusCodeValue());
    assertEquals("123", result.getBody().getId());
    assertEquals("John", result.getBody().getFirstName());
    assertEquals("Doe", result.getBody().getLastName());
    verify(studentService, times(1)).addStudent(any(Student.class));
  }

  @Test
  void shouldFetchOneStudent() {
    Student student = Student.builder()
        .id("123")
        .firstName("John")
        .lastName("Doe")
        .build();

    when(studentService.findStudentById("123")).thenReturn(student);

    ResponseEntity<Student> result = studentController.fetchOneStudent("123");

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("123", result.getBody().getId());
    assertEquals("John", result.getBody().getFirstName());
    assertEquals("Doe", result.getBody().getLastName());
    verify(studentService, times(1)).findStudentById("123");
  }

  @Test
  void shouldUpdateOneStudent() {
    Student student = Student.builder()
        .id("123")
        .firstName("John")
        .lastName("Doe")
        .build();

    when(studentService.updateStudentById(any(Student.class))).thenReturn(student);

    ResponseEntity<Student> result = studentController.updateOneStudent(student);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("123", result.getBody().getId());
    assertEquals("John", result.getBody().getFirstName());
    assertEquals("Doe", result.getBody().getLastName());
    verify(studentService, times(1)).updateStudentById(any(Student.class));
  }

  @Test
  void shouldDeleteOneStudent() {
    String studentId = "123";

    when(studentService.deleteStudentById(studentId)).thenReturn(true);

    ResponseEntity<Void> result = studentController.deleteOneStudent(studentId);

    assertEquals(204, result.getStatusCodeValue());
    verify(studentService, times(1)).deleteStudentById(studentId);
  }
}
