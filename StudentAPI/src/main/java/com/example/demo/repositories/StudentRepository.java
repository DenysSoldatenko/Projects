package com.example.demo.repositories;

import com.example.demo.models.Student;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing Student entities in MongoDB.
 */
public interface StudentRepository extends MongoRepository<Student, String> {

  boolean existsById(String id);

  Optional<Student> findByEmail(String email);
}