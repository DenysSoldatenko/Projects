package com.example.demo.services;

import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for managing student-related operations.
 */
@AllArgsConstructor
@Service
public class StudentService {

  private final StudentRepository studentRepository;

  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  public Optional<Student> findStudentById(String id) {
    return studentRepository.findById(id);
  }

  /**
   * Updates the information of a student.
   *
   * @param student The student with updated information.
   * @return The updated student.
   * @throws RuntimeException if the student is not found.
   */
  public Student updateStudentById(Student student) {
    Optional<Student> existingStudentOptional = studentRepository.findById(student.getId());

    if (existingStudentOptional.isPresent()) {
      studentRepository.save(student);
      return student;
    } else {
      throw new RuntimeException("Student not found");
    }
  }

  /**
   * Adds a new student.
   *
   * @param student The student to be added.
   * @return The added student.
   * @throws RuntimeException if a student with the same ID already exists in the database.
   */
  public Student addStudent(Student student) {
    Optional<Student> existingStudentOptional = studentRepository.findById(student.getId());

    if (existingStudentOptional.isPresent()) {
      throw new RuntimeException("Database already have this student!");
    } else {
      studentRepository.save(student);
      return student;
    }
  }

  /**
   * Deletes a student by their unique identifier.
   *
   * @param id The unique identifier of the student to be deleted.
   * @throws RuntimeException if the student is not found.
   */
  public void deleteStudentById(String id) {
    Optional<Student> existingStudentOptional = studentRepository.findById(id);

    if (existingStudentOptional.isPresent()) {
      studentRepository.deleteById(id);
    } else {
      throw new RuntimeException("Student not found");
    }
  }
}