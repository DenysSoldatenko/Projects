package com.example.jwtsecuritysystem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.jwtsecuritysystem.dto.AuthenticationRequestDto;
import com.example.jwtsecuritysystem.models.Role;
import com.example.jwtsecuritysystem.models.User;
import com.example.jwtsecuritysystem.security.token.JwtTokenProvider;
import com.example.jwtsecuritysystem.services.impl.AuthenticationServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Unit tests for the AuthenticationServiceImpl class.
 */
@ExtendWith(SpringExtension.class)
class AuthenticationServiceImplTest {

  @Mock
  private AuthenticationManager authenticationManager;

  @Mock
  private JwtTokenProvider jwtTokenProvider;

  @Mock
  private UserService userService;

  private AuthenticationServiceImpl authenticationService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    authenticationService = new AuthenticationServiceImpl(authenticationManager, jwtTokenProvider, userService);
  }

  @Test
  void testLoginSuccessful() {
    final String username = "testUser";
    final String password = "password";
    final String token = "jwtToken";

    User user = new User();
    user.setUsername(username);
    Role roleUser = new Role();
    roleUser.setName("ROLE_USER");
    List<Role> userRoles = new ArrayList<>();
    userRoles.add(roleUser);

    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenReturn(mock(Authentication.class));
    when(userService.getByUsername(username)).thenReturn(user);
    when(jwtTokenProvider.createToken(username, user.getRoles())).thenReturn(token);

    AuthenticationRequestDto requestDto = new AuthenticationRequestDto(username, password);
    Map<Object, Object> response = authenticationService.login(requestDto);

    assertEquals(username, response.get("username"));
    assertEquals(token, response.get("token"));
    verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    verify(userService).getByUsername(username);
    verify(jwtTokenProvider).createToken(username, user.getRoles());
  }

  @Test
  void testLoginUserNotFound() {
    String username = "nonExistentUser";
    String password = "password";
    AuthenticationRequestDto requestDto = new AuthenticationRequestDto(username, password);

    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenThrow(new UsernameNotFoundException("User not found"));

    assertThrows(UsernameNotFoundException.class, () -> authenticationService.login(requestDto));
    verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
  }
}
