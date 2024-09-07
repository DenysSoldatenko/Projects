package com.example.demo.services;

import com.example.demo.models.Student;
import java.util.List;

/**
 * Service class for managing student-related operations.
 */
public interface StudentService {

  List<Student> getAllStudents();

  Student findStudentById(String id);

  Student updateStudentById(Student student);

  Student addStudent(Student student);

  void deleteStudentById(String id);

  String initializeData();
}
