package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.exeptions.StudentAlreadyExistsException;
import com.example.demo.exeptions.StudentNotFoundException;
import com.example.demo.initializers.StudentInitializer;
import com.example.demo.models.Address;
import com.example.demo.models.Gender;
import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.services.impl.StudentServiceImpl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Unit tests for the StudentService class.
 */
class StudentServiceImplTest {

  @InjectMocks
  private StudentServiceImpl studentService;

  @Mock
  private StudentRepository studentRepository;

  @Mock
  private StudentInitializer studentInitializer;

  private Student student;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    student = Student.builder()
      .id("1")
      .firstName("John")
      .lastName("Doe")
      .email("john.doe@example.com")
      .gender(Gender.MALE)
      .address(new Address())
      .favouriteSubjects(Arrays.asList("Math", "Science"))
      .totalSpentInBooks(BigDecimal.valueOf(100.0))
      .build();
  }

  @Test
  void testInitializeData() {
    when(studentInitializer.initDatabase()).thenReturn("Data initialized");
    String result = studentService.initializeData();
    assertEquals("Data initialized", result);
    verify(studentInitializer).initDatabase();
  }

  @Test
  void testGetAllStudents() {
    when(studentRepository.findAll()).thenReturn(Arrays.asList(student));
    List<Student> students = studentService.getAllStudents();
    assertEquals(1, students.size());
    assertEquals("John", students.get(0).getFirstName());
  }

  @Test
  void testFindStudentByIdFound() {
    when(studentRepository.findById("1")).thenReturn(Optional.of(student));
    Student foundStudent = studentService.findStudentById("1");
    assertEquals(student.getId(), foundStudent.getId());
  }

  @Test
  void testFindStudentByIdNotFound() {
    when(studentRepository.findById("2")).thenReturn(Optional.empty());
    assertThrows(StudentNotFoundException.class, () -> studentService.findStudentById("2"));
  }

  @Test
  void testUpdateStudentById() {
    when(studentRepository.findById("1")).thenReturn(Optional.of(student));
    when(studentRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());

    student.setFirstName("Jane");
    student.setEmail("jane.doe@example.com");

    when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> {
      Student updatedStudent = invocation.getArgument(0);
      return updatedStudent;
    });

    Student updatedStudent = studentService.updateStudentById(student);

    assertEquals("Jane", updatedStudent.getFirstName());
    verify(studentRepository).save(student);
  }

  @Test
  void testAddStudent() {
    when(studentRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());
    when(studentRepository.save(student)).thenReturn(student);

    Student addedStudent = studentService.addStudent(student);
    assertEquals(student.getId(), addedStudent.getId());
    verify(studentRepository).save(student);
  }

  @Test
  void testAddStudentAlreadyExists() {
    when(studentRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(student));

    assertThrows(StudentAlreadyExistsException.class, () -> studentService.addStudent(student));
  }

  @Test
  void testDeleteStudentByIdExists() {
    when(studentRepository.existsById("1")).thenReturn(true);
    doNothing().when(studentRepository).deleteById("1");

    boolean result = studentService.deleteStudentById("1");
    assertTrue(result);
    verify(studentRepository).deleteById("1");
  }

  @Test
  void testDeleteStudentByIdNotExists() {
    when(studentRepository.existsById("2")).thenReturn(false);

    boolean result = studentService.deleteStudentById("2");
    assertFalse(result);
    verify(studentRepository, never()).deleteById("2");
  }
}
