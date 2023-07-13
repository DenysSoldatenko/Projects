package com.example.pastebox.services;

import com.example.pastebox.models.PasteDto;
import com.example.pastebox.models.PasteResponse;
import java.util.List;

/**
 * Service interface for managing paste-related operations.
 */
public interface PasteService {
  PasteDto getByHash(String hash);

  List<PasteDto> getPublicPastes();

  PasteResponse add(PasteDto paste);

  void removeExpiredPastes();
}
