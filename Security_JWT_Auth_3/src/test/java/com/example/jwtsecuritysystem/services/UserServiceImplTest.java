package com.example.jwtsecuritysystem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.jwtsecuritysystem.dto.AdminDto;
import com.example.jwtsecuritysystem.dto.UserDto;
import com.example.jwtsecuritysystem.models.Role;
import com.example.jwtsecuritysystem.models.User;
import com.example.jwtsecuritysystem.repositories.RoleRepository;
import com.example.jwtsecuritysystem.repositories.UserRepository;
import com.example.jwtsecuritysystem.services.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    UserDto userDto = new UserDto();
    when(roleRepository.findByName("ROLE_USER")).thenReturn(roleUser);
    when(userRepository.save(user)).thenReturn(user);
    when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

    UserDto registeredUser = userService.createUser(user);

    assertEquals(userDto, registeredUser);
    verify(roleRepository).findByName("ROLE_USER");
    verify(userRepository).save(user);
    verify(modelMapper).map(user, UserDto.class);
  }

  @Test
  void testFindByUsername() {
    String username = "testUser";
    User user = new User();
    user.setUsername(username);
    when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

    User result = userService.getByUsername(username);

    assertEquals(username, result.getUsername());
    verify(userRepository).findByUsername(username);
  }

  @Test
  void testFindByIdUserFound() {
    Long userId = 1L;
    User user = new User();
    user.setId(userId);
    UserDto userDto = new UserDto();
    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

    UserDto result = userService.getById(userId);

    assertEquals(userDto, result);
    verify(userRepository).findById(userId);
    verify(modelMapper).map(user, UserDto.class);
  }

  @Test
  void testFindByIdUserNotFound() {
    Long userId = 1L;
    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> userService.getById(userId));
    verify(userRepository).findById(userId);
  }

  @Test
  void testCreateAdmin() {
    User user = new User();
    AdminDto adminDto = new AdminDto();
    when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(new Role());
    when(userRepository.save(user)).thenReturn(user);
    when(modelMapper.map(user, AdminDto.class)).thenReturn(adminDto);

    AdminDto createdAdmin = userService.createAdmin(user);

    assertEquals(adminDto, createdAdmin);
    verify(roleRepository).findByName("ROLE_ADMIN");
    verify(userRepository).save(user);
    verify(modelMapper).map(user, AdminDto.class);
  }

  @Test
  void testGetAdminById() {
    Long userId = 1L;
    User user = new User();
    AdminDto adminDto = new AdminDto();
    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(modelMapper.map(user, AdminDto.class)).thenReturn(adminDto);

    AdminDto result = userService.getAdminById(userId);

    assertEquals(adminDto, result);
    verify(userRepository).findById(userId);
    verify(modelMapper).map(user, AdminDto.class);
  }

  @Test
  void testRemoveUser() {
    Long userId = 1L;
    userService.removeUser(userId);

    verify(userRepository, Mockito.times(1)).deleteById(userId);
  }
}
