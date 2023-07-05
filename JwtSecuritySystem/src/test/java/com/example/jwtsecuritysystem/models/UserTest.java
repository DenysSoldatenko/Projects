package com.example.jwtsecuritysystem.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import jakarta.persistence.Column;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.lang.reflect.Field;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for the User class.
 */
@ExtendWith(MockitoExtension.class)
class UserTest {

  private User user;

  @BeforeEach
  void setUp() {
    user = new User();
    user.setId(1L);
    user.setUsername("john.doe");
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setEmail("john.doe@example.com");
    user.setPassword("password");
    user.setRoles(List.of(new Role()));
  }

  @Test
  void testIdProperty() {
    assertEquals(1L, user.getId());
  }

  @Test
  void testUsernameProperty() {
    assertEquals("john.doe", user.getUsername());
  }

  @Test
  void testFirstNameProperty() {
    assertEquals("John", user.getFirstName());
  }

  @Test
  void testLastNameProperty() {
    assertEquals("Doe", user.getLastName());
  }

  @Test
  void testEmailProperty() {
    assertEquals("john.doe@example.com", user.getEmail());
  }

  @Test
  void testPasswordProperty() {
    assertEquals("password", user.getPassword());
  }

  @Test
  void testRolesProperty() {
    assertEquals(1, user.getRoles().size());
  }

  @Test
  void testUsernameFieldAnnotations() {
    Field usernameField = getFieldByName("username");
    assertNotNull(usernameField.getAnnotation(Column.class));
  }

  @Test
  void testFirstNameFieldAnnotations() {
    Field firstNameField = getFieldByName("firstName");
    assertNotNull(firstNameField.getAnnotation(Column.class));
  }

  @Test
  void testLastNameFieldAnnotations() {
    Field lastNameField = getFieldByName("lastName");
    assertNotNull(lastNameField.getAnnotation(Column.class));
  }

  @Test
  void testEmailFieldAnnotations() {
    Field emailField = getFieldByName("email");
    assertNotNull(emailField.getAnnotation(Column.class));
  }

  @Test
  void testRolesFieldAnnotations() {
    Field rolesField = getFieldByName("roles");
    assertNotNull(rolesField.getAnnotation(ManyToMany.class));
    assertNotNull(rolesField.getAnnotation(JoinTable.class));
  }

  private Field getFieldByName(String fieldName) {
    Field[] fields = user.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (field.getName().equals(fieldName)) {
        return field;
      }
    }
    return null;
  }
}
