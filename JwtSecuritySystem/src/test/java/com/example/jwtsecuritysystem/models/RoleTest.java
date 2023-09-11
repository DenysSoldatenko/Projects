package com.example.jwtsecuritysystem.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.lang.reflect.Field;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for the Role class.
 */
@ExtendWith(MockitoExtension.class)
public class RoleTest {
  private Role role;

  @BeforeEach
  void setUp() {
    role = new Role();
    role.setId(1L);
    role.setName("ROLE_USER");
    role.setUsers(List.of(new User()));
  }

  @Test
  void testIdProperty() {
    assertEquals(1L, role.getId());
  }

  @Test
  void testNameProperty() {
    assertEquals("ROLE_USER", role.getName());
  }

  @Test
  void testUsersProperty() {
    assertEquals(1, role.getUsers().size());
  }

  @Test
  void testIdFieldAnnotations() {
    Field idField = getFieldByName("id");
    assertNotNull(idField.getAnnotation(Id.class));
    assertNotNull(idField.getAnnotation(GeneratedValue.class));
  }

  @Test
  void testNameFieldAnnotations() {
    Field nameField = getFieldByName("name");
    assertNotNull(nameField.getAnnotation(Column.class));
  }

  @Test
  void testUsersFieldAnnotations() {
    Field usersField = getFieldByName("users");
    assertNotNull(usersField.getAnnotation(ManyToMany.class));
  }

  private Field getFieldByName(String fieldName) {
    Field[] fields = role.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (field.getName().equals(fieldName)) {
        return field;
      }
    }
    return null;
  }
}
