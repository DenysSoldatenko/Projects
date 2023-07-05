package com.example.jwtsecuritysystem.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.lang.reflect.Field;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Unit tests for the BaseEntity class.
 */
class BaseEntityTest {
  private BaseEntity baseEntity;

  @BeforeEach
  void setUp() {
    baseEntity = new BaseEntity();
    baseEntity.setId(1L);
    baseEntity.setCreated(new Date());
    baseEntity.setUpdated(new Date());
    baseEntity.setStatus(Status.ACTIVE);
  }

  @Test
  void testIdProperty() {
    assertEquals(1L, baseEntity.getId());
  }

  @Test
  void testCreatedProperty() {
    assertNotNull(baseEntity.getCreated());
  }

  @Test
  void testUpdatedProperty() {
    assertNotNull(baseEntity.getUpdated());
  }

  @Test
  void testStatusProperty() {
    assertEquals(Status.ACTIVE, baseEntity.getStatus());
  }

  @Test
  void testIdFieldAnnotations() {
    Field idField = getFieldByName("id");
    assertNotNull(idField.getAnnotation(Id.class));
    assertNotNull(idField.getAnnotation(GeneratedValue.class));
  }

  @Test
  void testCreatedFieldAnnotations() {
    Field createdField = getFieldByName("created");
    assertNotNull(createdField.getAnnotation(CreatedDate.class));
    assertNotNull(createdField.getAnnotation(Column.class));
  }

  @Test
  void testUpdatedFieldAnnotations() {
    Field updatedField = getFieldByName("updated");
    assertNotNull(updatedField.getAnnotation(LastModifiedDate.class));
    assertNotNull(updatedField.getAnnotation(Column.class));
  }

  @Test
  void testStatusFieldAnnotations() {
    Field statusField = getFieldByName("status");
    assertNotNull(statusField.getAnnotation(Enumerated.class));
    assertNotNull(statusField.getAnnotation(Column.class));
  }

  private Field getFieldByName(String fieldName) {
    Field[] fields = baseEntity.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (field.getName().equals(fieldName)) {
        return field;
      }
    }
    return null;
  }
}
