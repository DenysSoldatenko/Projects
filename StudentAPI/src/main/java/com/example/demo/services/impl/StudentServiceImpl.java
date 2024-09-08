package com.example.demo.services.impl;

import com.example.demo.exeptions.StudentAlreadyExistsException;
import com.example.demo.exeptions.StudentNotFoundException;
import com.example.demo.initializers.StudentInitializer;
import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.services.StudentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing student-related operations.
 */
@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final StudentInitializer studentInitializer;

  @Override
  public String initializeData() {
    return studentInitializer.initDatabase();
  }

  @Override
  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  @Override
  public Student findStudentById(String id) {
    return studentRepository.findById(id)
        .orElseThrow(() -> new StudentNotFoundException("Student not found!"));
  }

  /**
   * Updates the information of a student.
   *
   * @param student The student with updated information.
   * @return The updated student.
   * @throws RuntimeException if the student is not found.
   */
  @Override
  public Student updateStudentById(Student student) {
    System.out.println(student);
    Student existingStudent = findStudentById(student.getId());
    existingStudent.setFirstName(student.getFirstName());
    existingStudent.setLastName(student.getLastName());
    existingStudent.setEmail(student.getEmail());
    existingStudent.setGender(student.getGender());
    existingStudent.setAddress(student.getAddress());
    existingStudent.setFavouriteSubjects(student.getFavouriteSubjects());
    existingStudent.setTotalSpentInBooks(student.getTotalSpentInBooks());
    return studentRepository.save(existingStudent);
  }

  /**
   * Adds a new student.
   *
   * @param student The student to be added.
   * @return The added student.
   * @throws RuntimeException if a student with the same ID already exists in the database.
   */
  @Override
  public Student addStudent(Student student) {
    System.out.println(student);
    if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
      throw new StudentAlreadyExistsException("A student with this email already exists!");
    }
    return studentRepository.save(student);
  }

  /**
   * Deletes a student by their unique identifier.
   *
   * @param id The unique identifier of the student to be deleted.
   * @throws RuntimeException if the student is not found.
   */
  @Override
  public boolean deleteStudentById(String id) {
    if (studentRepository.existsById(id)) {
      studentRepository.deleteById(id);
      return true;
    }
    return false;
  }
}