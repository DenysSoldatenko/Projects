package com.example.jwtsecuritysystem.configurations;

import com.example.jwtsecuritysystem.models.Role;
import com.example.jwtsecuritysystem.models.Status;
import com.example.jwtsecuritysystem.models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@NoArgsConstructor
public final class JwtUserFactory {

  public static JwtUser create(User user) {
    return JwtUser.builder()
    .id(user.getId())
    .username(user.getUsername())
    .firstName(user.getFirstName())
    .lastName(user.getLastName())
    .email(user.getLastName())
    .password(user.getPassword())
    .authorities(mapToGrantedAuthorities(new ArrayList<>(user.getRoles())))
    .enabled(user.getStatus().equals(Status.ACTIVE))
    .lastPasswordResetDate(user.getUpdated())
    .build();
  }

  private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
    return userRoles.stream()
    .map(role -> new SimpleGrantedAuthority(role.getName()))
    .collect(Collectors.toList());
  }
}