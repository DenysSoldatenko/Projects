package com.example.demo.services;

import com.example.demo.exeptions.StudentNotFoundException;
import com.example.demo.models.Student;
import java.util.List;

/**
 * Service interface for managing student-related operations.
 * This interface provides methods for retrieving, adding, updating, and deleting students.
 */
public interface StudentService {

  /**
   * Retrieves a list of all students.
   *
   * @return A list of all students in the system.
   */
  List<Student> getAllStudents();

  /**
   * Finds a student by their unique ID.
   *
   * @param id The unique identifier of the student.
   * @return The student with the given ID.
   * @throws StudentNotFoundException if the student is not found.
   */
  Student findStudentById(String id);

  /**
   * Updates the information of an existing student by their unique ID.
   *
   * @param student The student object containing updated information.
   * @return The updated student object.
   * @throws StudentNotFoundException if the student with the given ID does not exist.
   */
  Student updateStudentById(Student student);

  /**
   * Adds a new student to the system.
   *
   * @param student The student object to be added.
   * @return The added student object.
   */
  Student addStudent(Student student);

  /**
   * Deletes a student from the system by their unique ID.
   *
   * @param id The unique identifier of the student to be deleted.
   * @return true if the student was successfully deleted, false otherwise.
   */
  boolean deleteStudentById(String id);

  /**
   * Initializes or preloads data (e.g., creating sample students).
   *
   * @return A status message indicating the result of the initialization process.
   */
  String initializeData();
}
