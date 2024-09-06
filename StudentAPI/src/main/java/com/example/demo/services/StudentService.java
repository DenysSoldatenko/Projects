package com.example.demo.services;

import com.example.demo.models.Student;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing student-related operations.
 */
public interface StudentService {

  List<Student> getAllStudents();

  Optional<Student> findStudentById(String id);

  Student updateStudentById(Student student);

  Student addStudent(Student student);

  void deleteStudentById(String id);

  String initializeData();
}
