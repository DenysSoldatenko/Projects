package com.example.jwtsecuritysystem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.jwtsecuritysystem.dto.UserDto;
import com.example.jwtsecuritysystem.models.Role;
import com.example.jwtsecuritysystem.models.User;
import com.example.jwtsecuritysystem.repositories.RoleRepository;
import com.example.jwtsecuritysystem.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.jwtsecuritysystem.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Unit tests for the UserServiceImpl class.
 */
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private RoleRepository roleRepository;

  @Mock
  private BCryptPasswordEncoder passwordEncoder;

  @Mock
  public ModelMapper modelMapper;

  private UserServiceImpl userService;

  @BeforeEach
  public void setUp() {
    userService = new UserServiceImpl(modelMapper, userRepository, roleRepository, passwordEncoder);
  }

  @Test
  void testRegisterUser() {
    Role roleUser = new Role();
    roleUser.setName("ROLE_USER");
    List<Role> userRoles = new ArrayList<>();
    userRoles.add(roleUser);

    User user = new User();
    when(roleRepository.findByName("ROLE_USER")).thenReturn(roleUser);
    when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
    when(userRepository.save(user)).thenReturn(user);

    UserDto registeredUser = userService.createUser(user);

  }

//  @Test
//  void testFindByUsername() {
//    String username = "testUser";
//    User user = new User();
//    user.setUsername(username);
//    when(userRepository.findByUsername(username)).thenReturn(user);
//
//    UserDto result = userService.getByUsername(username);
//
//    assertEquals(username, result.getUsername());
//  }

  @Test
  void testFindByIdUserFound() {
    Long userId = 1L;
    User user = new User();
    user.setId(userId);
    when(userRepository.findById(userId)).thenReturn(Optional.of(user));

    UserDto result = userService.getById(userId);

    assertEquals(userId, result.getId());
  }

  @Test
  void testFindByIdUserNotFound() {
    Long userId = 1L;
    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> userService.getById(userId));
  }

  @Test
  void testDeleteUser() {
    Long userId = 1L;
    userService.removeUser(userId);

    verify(userRepository, Mockito.times(1)).deleteById(userId);
  }
}
