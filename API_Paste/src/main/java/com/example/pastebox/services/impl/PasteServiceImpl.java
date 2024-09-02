package com.example.pastebox.services.impl;

import static com.example.pastebox.models.PublicStatus.PUBLIC;
import static java.time.LocalDateTime.now;

import com.example.pastebox.exceptions.PasteNotFoundException;
import com.example.pastebox.models.Paste;
import com.example.pastebox.dtos.PasteDto;
import com.example.pastebox.dtos.PasteResponse;
import com.example.pastebox.repositories.PasteRepository;
import com.example.pastebox.services.PasteService;
import com.example.pastebox.utils.PasteFactory;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing paste-related operations.
 */
@Service
@RequiredArgsConstructor
public class PasteServiceImpl implements PasteService {

  public final ModelMapper modelMapper;
  private final PasteRepository pasteRepository;

  @Override
  public PasteDto getPasteByHash(String hash) {
    Optional<Paste> optionalPaste = pasteRepository.findByShortLink(hash);
    return optionalPaste.map(p -> modelMapper.map(p, PasteDto.class))
      .orElseThrow(() -> new PasteNotFoundException("There is no paste with this hash!"));
  }

  @Override
  public List<PasteDto> getPublicPastes() {
    List<Paste> publicPastes = pasteRepository.findTop10ByPublicStatusOrderByCreationTimeDesc(PUBLIC);

    return publicPastes.stream()
    .map(paste -> modelMapper.map(paste, PasteDto.class))
    .toList();
  }

  @Override
  public PasteResponse createPaste(PasteDto pasteDto) {
    Paste paste = PasteFactory.generatePaste(pasteDto);
    pasteRepository.save(paste);
    return new PasteResponse(paste.getShortLink());
  }

  @Override
  @Scheduled(fixedRate = 600_000) // Перевірка кожні 10 хвилин (600 000 мс)
  public void removeExpiredPastes() {
    List<Paste> expiredPastes = pasteRepository.findByExpirationTimeLessThanEqual(now());

    Iterator<Paste> iterator = expiredPastes.iterator();
    while (iterator.hasNext()) {
      Paste paste = iterator.next();
      pasteRepository.delete(paste);
      iterator.remove();
    }
  }
}
