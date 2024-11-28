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
 *
 * <p>This repository provides methods to interact with the database and manage
 * the persistence of {@link ConfirmationToken} entities. It includes operations
 * such as finding tokens by their string value, updating the confirmation time,
 * and retrieving the first confirmed token for a user.
 */
@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

  /**
   * Finds a confirmation token by its string value.
   *
   * @param token the token string.
   * @return an Optional containing the ConfirmationToken if found, otherwise empty.
   */
  Optional<ConfirmationToken> findByToken(String token);

  /**
   * Updates the confirmedAt field of a confirmation token.
   *
   * @param token the token string.
   * @param confirmedAt the timestamp when the token was confirmed.
   */
  @Modifying
  @Transactional
  @Query("UPDATE ConfirmationToken c SET c.confirmedAt = :confirmedAt WHERE c.token = :token")
  void updateConfirmedAt(@Param("token") String token,
                         @Param("confirmedAt") LocalDateTime confirmedAt);

  /**
   * Finds the first confirmation token associated with a user that has been confirmed.
   *
   * @param appUser the user whose confirmed token is being searched for.
   * @return an Optional containing the ConfirmationToken if found, otherwise empty.
   */
  Optional<ConfirmationToken> findFirstByUserAndConfirmedAtIsNotNull(User appUser);
}
