package com.example.pastebox.services;

import com.example.pastebox.models.Paste;
import com.example.pastebox.models.PasteDto;
import com.example.pastebox.models.PasteResponse;
import com.example.pastebox.models.PublicStatus;
import com.example.pastebox.repositories.PasteRepository;
import com.example.pastebox.utils.ExpirationTimeGenerator;
import com.example.pastebox.utils.LinkGenerator;
import com.example.pastebox.utils.PasteNotFoundException;
import java.time.LocalDateTime;
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

  private final PasteRepository pasteRepository;

  public final ModelMapper modelMapper;

  @Override
  public PasteDto getByHash(String hash) {
    Optional<Paste> optionalPaste = pasteRepository.findByShortLink(hash);

    if (optionalPaste.isPresent()) {
      return modelMapper.map(optionalPaste.get(), PasteDto.class);
    } else {
      throw new PasteNotFoundException("There is no paste with this hash!");
    }
  }

  @Override
  public List<PasteDto> getPublicPastes() {
    List<Paste> publicPastes = pasteRepository
        .findTop10ByPublicStatusOrderByCreationTimeDesc(PublicStatus.PUBLIC);

    return publicPastes.stream()
    .map(paste -> modelMapper.map(paste, PasteDto.class))
    .toList();
  }

  @Override
  public PasteResponse add(PasteDto pasteDto) {
    Paste paste = Paste.builder()
        .content(pasteDto.getContent())
        .creationTime(pasteDto.getCreationTime())
        .expirationTime(
          ExpirationTimeGenerator.generate(
          pasteDto.getCreationTime(), pasteDto.getExpirationDuration()
          )
        )
        .expirationDuration(pasteDto.getExpirationDuration())
        .publicStatus(pasteDto.getPublicStatus())
        .shortLink(LinkGenerator.generate())
        .build();

    pasteRepository.save(paste);

    return new PasteResponse(paste.getShortLink());
  }

  @Override
  @Scheduled(fixedRate = 600_000) // Перевірка кожні 10 хвилин (600 000 мс)
  public void removeExpiredPastes() {
    LocalDateTime currentTime = LocalDateTime.now();
    List<Paste> expiredPastes = pasteRepository.findByExpirationTimeLessThanEqual(currentTime);

    Iterator<Paste> iterator = expiredPastes.iterator();
    while (iterator.hasNext()) {
      Paste paste = iterator.next();
      pasteRepository.delete(paste);
      iterator.remove();
    }
  }

}
