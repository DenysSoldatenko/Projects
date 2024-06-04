package com.example.securitysystem.repositories;

import com.example.securitysystem.entities.ConfirmationToken;
import com.example.securitysystem.entities.User;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository interface for managing confirmation tokens.
 */
@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

  Optional<ConfirmationToken> findByToken(String token);

  @Modifying
  @Transactional
  @Query("UPDATE ConfirmationToken c SET c.confirmedAt = :confirmedAt WHERE c.token = :token")
  void updateConfirmedAt(@Param("token") String token,
                         @Param("confirmedAt") LocalDateTime confirmedAt);

  Optional<ConfirmationToken> findFirstByUserAndConfirmedAtIsNotNull(User appUser);
}