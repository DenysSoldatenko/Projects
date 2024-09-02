package com.example.pastebox.services;

import com.example.pastebox.dtos.PasteDto;
import com.example.pastebox.dtos.PasteResponse;
import java.util.List;

/**
 * Service interface for managing paste-related operations.
 */
public interface PasteService {

  PasteDto getPasteByHash(String hash);

  List<PasteDto> getPublicPastes();

  PasteResponse createPaste(PasteDto paste);

  void removeExpiredPastes();
}
