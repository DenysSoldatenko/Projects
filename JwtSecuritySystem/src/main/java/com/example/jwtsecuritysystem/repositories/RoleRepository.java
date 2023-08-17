package com.example.jwtsecuritysystem.repositories;

import com.example.jwtsecuritysystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}