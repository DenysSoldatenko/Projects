package com.example.jwtsecuritysystem.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.jwtsecuritysystem.models.Role;
import com.example.jwtsecuritysystem.models.Status;
import com.example.jwtsecuritysystem.models.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Unit tests for the JwtUserDetailsService class.
 */
public class JwtUserFactoryTest {

  @Test
  public void testNoArgsConstructor() {
    JwtUserFactory jwtUserFactory = new JwtUserFactory();
    assertNotNull(jwtUserFactory);
  }

  @Test
  public void testCreateJwtUserFromUser() {
    User user = new User();
    user.setId(1L);
    user.setUsername("testUser");
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setEmail("test@example.com");
    user.setPassword("password");
    user.setStatus(Status.ACTIVE);
    user.setUpdated(null); // Set to null for simplicity

    Role role = new Role();
    role.setName("ROLE_USER");
    List<Role> roles = new ArrayList<>();
    roles.add(role);
    user.setRoles(roles);

    JwtUser jwtUser = JwtUserFactory.create(user);

    assertNotNull(jwtUser);
    assertEquals(user.getId(), jwtUser.getId());
    assertEquals(user.getUsername(), jwtUser.getUsername());
    assertEquals(user.getFirstName(), jwtUser.getFirstName());
    assertEquals(user.getLastName(), jwtUser.getLastName());
    assertEquals(user.getEmail(), jwtUser.getEmail());
    assertEquals(user.getPassword(), jwtUser.getPassword());
    assertEquals(true, jwtUser.isEnabled());

    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
    Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
    assertNotNull(authorities);
    assertEquals(1, authorities.size());
    assertEquals(authority, authorities.iterator().next());
  }
}
