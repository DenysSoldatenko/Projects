package com.example.pastebox.services;

import com.example.pastebox.models.PasteDto;
import com.example.pastebox.models.PasteResponse;
import java.util.List;

public interface PasteService {
  PasteDto getByHash(String hash);

  List<PasteDto> getPublicPastes();

  PasteResponse add(PasteDto paste);

  void removeExpiredPastes();
}
