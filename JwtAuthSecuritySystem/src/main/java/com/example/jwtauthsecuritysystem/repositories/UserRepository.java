package com.example.jwtauthsecuritysystem.repositories;

import com.example.jwtauthsecuritysystem.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);
}