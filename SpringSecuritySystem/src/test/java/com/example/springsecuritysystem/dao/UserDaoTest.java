//package com.example.springsecuritysystem.dao;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
///**
// * Unit tests for the UserDao class.
// */
//class UserDaoTest {
//
//  private UserDao userDao;
//
//  @BeforeEach
//  public void setUp() {
//    userDao = new UserDao();
//  }
//
//  @Test
//  void shouldFindUserByEmail() {
//    String email = "john.doe@gmail.com";
//    UserDetails userDetails = userDao.findUserByEmail(email);
//
//    assertEquals(email, userDetails.getUsername());
//    assertEquals(1, userDetails.getAuthorities().size());
//    assertEquals("ROLE_ADMIN", userDetails.getAuthorities().iterator().next().getAuthority());
//  }
//
//  @Test
//  void shouldThrowUsernameNotFoundExceptionForNonexistentUser() {
//    String email = "nonexistent@gmail.com";
//    assertThrows(UsernameNotFoundException.class, () -> userDao.findUserByEmail(email));
//  }
//}
