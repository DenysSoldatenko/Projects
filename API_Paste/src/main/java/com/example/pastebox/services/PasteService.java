package com.example.pastebox.services;

import com.example.pastebox.dtos.PasteDto;
import com.example.pastebox.dtos.PasteResponse;
import java.util.List;

/**
 * Service interface for managing paste-related operations in the PasteBox application.
 * Provides methods for retrieving, creating, and removing pastes.
 */
public interface PasteService {

  /**
   * Retrieves a paste by its unique hash identifier.
   *
   * @param hash The unique identifier of the paste.
   * @return The PasteDto object containing the paste data.
   *         Returns null if no paste is found with the given hash.
   */
  PasteDto getPasteByHash(String hash);

  /**
   * Retrieves all public pastes.
   *
   * @return A list of PasteDto objects representing public pastes.
   */
  List<PasteDto> getPublicPastes();

  /**
   * Creates a new paste.
   *
   * @param paste The PasteDto object containing the data for the new paste.
   * @return A PasteResponse object containing the details of the created paste,
   *         such as its unique hash or status of creation.
   */
  PasteResponse createPaste(PasteDto paste);

  /**
   * Removes expired pastes from the system.
   * This method is typically called periodically to clean up expired data.
   */
  void removeExpiredPastes();
}
