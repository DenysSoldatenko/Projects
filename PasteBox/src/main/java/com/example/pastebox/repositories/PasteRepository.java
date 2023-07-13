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
 */
@Repository
public interface PasteRepository extends JpaRepository<Paste, Integer> {

  Optional<Paste> findByShortLink(String link);

  List<Paste> findTop10ByPublicStatusOrderByCreationTimeDesc(PublicStatus accessType);

  List<Paste> findByExpirationTimeLessThanEqual(LocalDateTime now);
}
