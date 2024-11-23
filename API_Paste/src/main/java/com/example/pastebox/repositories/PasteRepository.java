package com.example.pastebox.repositories;

import com.example.pastebox.models.Paste;
import com.example.pastebox.models.PublicStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Paste entities in the database.
 * Provides methods to query and manipulate Paste records.
 */
@Repository
public interface PasteRepository extends JpaRepository<Paste, Integer> {

  /**
   * Finds a paste by its short link (unique identifier).
   *
   * @param link The unique short link of the paste.
   * @return An Optional containing the Paste if found, or an empty Optional if not found.
   */
  Optional<Paste> findByShortLink(String link);

  /**
   * Retrieves the top 10 most recent pastes with a specific public access status.
   *
   * @param accessType The access status (e.g., public or private).
   * @return A list of the top 10 most recent pastes with the specified access status.
   */
  List<Paste> findTop10ByPublicStatusOrderByCreationTimeDesc(PublicStatus accessType);

  /**
   * Finds all pastes that have expired (based on the expiration time).
   *
   * @param now The current date and time, used to compare against paste expiration times.
   * @return A list of pastes whose expiration time is less than or equal to the provided `now` time.
   */
  List<Paste> findByExpirationTimeLessThanEqual(LocalDateTime now);
}
