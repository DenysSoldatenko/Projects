package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for the StudentService class.
 */
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

  @Mock
  private StudentRepository studentRepository;

  @InjectMocks
  private StudentService studentService;

  private Student sampleStudent;

  /**
   * Set up a sample Student object for testing.
   */
  @BeforeEach
  public void setUp() {
    sampleStudent = Student.builder()
    .id("123")
    .firstName("John")
    .lastName("Doe")
    .build();
  }

  @Test
  public void shouldGetAllStudents() {
    when(studentRepository.findAll()).thenReturn(List.of(sampleStudent));

    var students = studentService.getAllStudents();

    assertEquals(1, students.size());
    assertEquals(sampleStudent, students.get(0));
  }

  @Test
  public void shouldFindStudentById() {
    when(studentRepository.findById("1")).thenReturn(Optional.of(sampleStudent));

    var result = studentService.findStudentById("1");

    assertTrue(result.isPresent());
    assertEquals(sampleStudent, result.get());
  }

  @Test
  public void shouldNotFindStudentById() {
    when(studentRepository.findById("1")).thenReturn(Optional.empty());

    var result = studentService.findStudentById("1");

    assertTrue(result.isEmpty());
  }

  @Test
  public void shouldUpdateStudentById() {
    when(studentRepository.findById(sampleStudent.getId())).thenReturn(Optional.of(sampleStudent));
    when(studentRepository.save(sampleStudent)).thenReturn(sampleStudent);

    var result = studentService.updateStudentById(sampleStudent);

    assertEquals(sampleStudent, result);
  }

  @Test
  public void shouldNotUpdateStudentByIdIfNotExists() {
    when(studentRepository.findById(sampleStudent.getId())).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> studentService.updateStudentById(sampleStudent));
  }

  @Test
  public void shouldAddStudent() {
    when(studentRepository.findById(sampleStudent.getId())).thenReturn(Optional.empty());
    when(studentRepository.save(sampleStudent)).thenReturn(sampleStudent);

    var result = studentService.addStudent(sampleStudent);

    assertEquals(sampleStudent, result);
  }

  @Test
  public void shouldNotAddStudentIfAlreadyExists() {
    when(studentRepository.findById(sampleStudent.getId())).thenReturn(Optional.of(sampleStudent));

    assertThrows(RuntimeException.class, () -> studentService.addStudent(sampleStudent));
  }

  @Test
  public void shouldDeleteStudentById() {
    when(studentRepository.findById(sampleStudent.getId())).thenReturn(Optional.of(sampleStudent));

    assertDoesNotThrow(() -> studentService.deleteStudentById(sampleStudent.getId()));
  }

  @Test
  public void shouldNotDeleteStudentByIdIfNotExists() {
    when(studentRepository.findById(sampleStudent.getId())).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class,
        () -> studentService.deleteStudentById(sampleStudent.getId()));
  }
}
