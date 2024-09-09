package com.example.demo.controllers;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

import com.example.demo.models.Student;
import com.example.demo.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling student-related operations.
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/students")
@Tag(name = "Student Controller", description = "API for managing student data")
public class StudentController {

  private final StudentService studentService;

  @Operation(
      summary = "Initialize student data",
      description = "Initializes the database with sample student data"
  )
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Data initialized successfully"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @PostMapping("/initialize")
  public ResponseEntity<String> initializeData() {
    return new ResponseEntity<>(studentService.initializeData(), CREATED);
  }

  @Operation(
      summary = "Fetch all students",
      description = "Retrieves a list of all students in the database"
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Successfully fetched all students"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping
  public ResponseEntity<List<Student>> fetchAllStudents() {
    return new ResponseEntity<>(studentService.getAllStudents(), OK);
  }

  @Operation(
      summary = "Add a new student",
      description = "Adds a new student to the database"
  )
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Student added successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid input"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @PostMapping("/addStudent")
  public ResponseEntity<Student> addStudent(@RequestBody Student student) {
    return new ResponseEntity<>(studentService.addStudent(student), CREATED);
  }

  @Operation(
      summary = "Fetch a single student by ID",
      description = "Retrieves a student from the database by their ID"
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Successfully fetched the student"),
    @ApiResponse(responseCode = "404", description = "Student not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping("/{id}")
  public ResponseEntity<Student> fetchOneStudent(@PathVariable String id) {
    return new ResponseEntity<>(studentService.findStudentById(id), OK);
  }

  @Operation(
      summary = "Update a student",
      description = "Updates an existing student's details"
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Student updated successfully"),
      @ApiResponse(responseCode = "404", description = "Student not found"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @PutMapping("/update")
  public ResponseEntity<Student> updateOneStudent(@RequestBody Student student) {
    return new ResponseEntity<>(studentService.updateStudentById(student), OK);
  }

  @Operation(
      summary = "Delete a student",
      description = "Deletes a student from the database by their ID"
  )
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
      @ApiResponse(responseCode = "404", description = "Student not found"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOneStudent(@PathVariable String id) {
    return studentService.deleteStudentById(id) ? noContent().build() : notFound().build();
  }
}
