package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.models.Student;
import com.example.demo.services.StudentService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
  void shouldFetchAllStudents() {
    List<Student> students = new ArrayList<>();
    Student student1 = Student.builder()
        .id("123")
        .firstName("John")
        .lastName("Doe")
        .build();

    Student student2 = Student.builder()
        .id("1234")
        .firstName("Jane")
        .lastName("Doe")
        .build();

    students.add(student1);
    students.add(student2);

    when(studentService.getAllStudents()).thenReturn(students);

    List<Student> result = studentController.fetchAllStudents();

    assertEquals(2, result.size());
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

    Student result = studentController.addStudent(student);

    assertEquals("123", result.getId());
    assertEquals("John", result.getFirstName());
    assertEquals("Doe", result.getLastName());
    verify(studentService, times(1)).addStudent(any(Student.class));
  }

  @Test
  void shouldFetchOneStudent() {
    Student student = Student.builder()
        .id("123")
        .firstName("John")
        .lastName("Doe")
        .build();

    when(studentService.findStudentById("1")).thenReturn(Optional.of(student));

    Student result = studentController.fetchOneStudent("1");

    assertEquals("123", result.getId());
    assertEquals("John", result.getFirstName());
    assertEquals("Doe", result.getLastName());
    verify(studentService, times(1)).findStudentById("1");
  }

  @Test
  void shouldFetchOneStudentNotFound() {
    when(studentService.findStudentById("1")).thenReturn(Optional.empty());

    try {
      studentController.fetchOneStudent("1");
    } catch (RuntimeException e) {
      assertEquals("Student not found", e.getMessage());
    }
  }

  @Test
  void shouldUpdateOneStudent() {
    Student student = Student.builder()
        .id("123")
        .firstName("John")
        .lastName("Doe")
        .build();

    when(studentService.updateStudentById(any(Student.class))).thenReturn(student);

    Student result = studentController.updateOneStudent(student);

    assertEquals("123", result.getId());
    assertEquals("John", result.getFirstName());
    assertEquals("Doe", result.getLastName());
    verify(studentService, times(1)).updateStudentById(any(Student.class));
  }

  @Test
  void shouldDeleteOneStudent() {
    String studentId = "1";

    doNothing().when(studentService).deleteStudentById(studentId);

    try {
      studentController.deleteOneStudent(studentId);
    } catch (Exception e) {
      fail("An exception was thrown: " + e.getMessage());
    }

    verify(studentService, times(1)).deleteStudentById(studentId);
  }
}
