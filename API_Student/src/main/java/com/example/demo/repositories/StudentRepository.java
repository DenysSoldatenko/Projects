package com.example.demo.repositories;

import com.example.demo.models.Student;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing Student entities in MongoDB.
 * This interface extends MongoRepository to provide CRUD operations for the Student entity.
 */
public interface StudentRepository extends MongoRepository<Student, String> {

  /**
   * Checks if a student exists by their unique identifier (ID).
   *
   * @param id The unique identifier of the student.
   * @return true if a student with the specified ID exists, false otherwise.
   */
  boolean existsById(String id);

  /**
   * Retrieves a student by their email address.
   *
   * @param email The email address of the student.
   * @return An Optional containing the Student if found, or an empty Optional if no student
   *         with the given email exists.
   */
  Optional<Student> findByEmail(String email);
}
