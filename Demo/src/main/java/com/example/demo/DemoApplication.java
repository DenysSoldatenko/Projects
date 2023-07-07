package com.example.demo;

import com.example.demo.models.Address;
import com.example.demo.models.Gender;
import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * The main class for the Demo application.
 * This class is responsible for starting the Spring Boot application.
 */
@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  /*  @Bean
  CommandLineRunner runner(StudentRepository repository) {
    return args -> {
      Faker faker = new Faker();

      for (int i = 1; i < 11; i++) {
        Address address = new Address(
            faker.address().country(),
            faker.address().city(),
            faker.address().zipCode()
        );

        List<String> favoriteSubjects = new ArrayList<>();
        favoriteSubjects.add(faker.educator().course());
        favoriteSubjects.add(faker.educator().course());

        Student student = Student.builder()
            .id(String.valueOf(i))
            .firstName(faker.name().firstName())
            .lastName(faker.name().lastName())
            .email(faker.internet().emailAddress())
            .gender(faker.options().option(Gender.class))
            .address(address)
            .favouriteSubjects(favoriteSubjects)
            .totalSpentInBooks(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 1000)))
            .created(LocalDateTime.now())
            .build();

        repository.insert(student);
      }
    };
  }*/

  /*  @Bean
  CommandLineRunner testIndex(StudentRepository repository,
                              MongoTemplate mongoTemplate) {
    return args -> {
      Address address = new Address("England", "London", "NE9");
      String email = "jamila@gmal.com";

      Student student = Student.builder()
          .id("115")
          .firstName("Jamila")
          .lastName("Ahmed")
          .email(email)
          .gender(Gender.FEMALE)
          .address(address)
          .favouriteSubjects(List.of("Computer science", "Maths"))
          .totalSpentInBooks(BigDecimal.TEN)
          .created(LocalDateTime.now())
          .build();

      // usingMongoTemplateAndQueries(repository, mongoTemplate, email, student);

      repository.findStudentByEmail(email)
          .ifPresentOrElse(
            s -> System.out.println(s + " already exists"),
            () -> {
              System.out.println("Inserting student " + student);
              repository.insert(student);
            }
          );
    };
  }*/

  private static void usingMongoTemplateAndQueries(StudentRepository repository,
                                                   MongoTemplate mongoTemplate,
                                                   String email, Student student) {
    Query query = new Query();
    query.addCriteria(Criteria.where("email").is(email));

    List<Student> students = mongoTemplate.find(query, Student.class);
    if (students.size() > 1) {
      throw new IllegalStateException("found many students with email " + email);
    }
    if (students.isEmpty()) {
      System.out.println("Inserting student: " + student);
      repository.insert(student);
    } else {
      System.out.println(student + " already exists");
    }
  }
}
