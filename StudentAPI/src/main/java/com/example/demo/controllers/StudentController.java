package com.example.demo.controllers;

import com.example.demo.models.Student;
import com.example.demo.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Controller class for handling student-related operations.
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/vi/students")
public class StudentController {

  private final StudentService studentService;

  @PostMapping("/initialize")
  @Operation(summary = "Initialize account data", description = "Initializes the data for accounts")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Data initialized successfully"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public ResponseEntity<String> initializeData() {
    return new ResponseEntity<>(studentService.initializeData(), CREATED);
  }

  @GetMapping
  public List<Student> fetchAllStudents() {
    return studentService.getAllStudents();
  }

  @PostMapping("/addStudent")
  public Student addStudent(@RequestBody Student student) {
    return studentService.addStudent(student);
  }

  @GetMapping("/{id}")
  public Student fetchOneStudent(@PathVariable String id) {
    return studentService.findStudentById(id)
    .orElseThrow(() -> new RuntimeException("Student not found"));
  }

  @PutMapping("/update")
  public Student updateOneStudent(@RequestBody Student student) {
    return studentService.updateStudentById(student);
  }

  @DeleteMapping("/{id}")
  public void deleteOneStudent(@PathVariable String id) {
    studentService.deleteStudentById(id);
  }
}