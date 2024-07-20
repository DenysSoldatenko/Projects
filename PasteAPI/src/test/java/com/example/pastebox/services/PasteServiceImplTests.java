package com.example.pastebox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.pastebox.models.ExpirationTime;
import com.example.pastebox.models.Paste;
import com.example.pastebox.models.PasteDto;
import com.example.pastebox.models.PasteResponse;
import com.example.pastebox.models.PublicStatus;
import com.example.pastebox.repositories.PasteRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

/**
 * Unit tests for the PasteServiceImpl class.
 */
@ExtendWith(MockitoExtension.class)
class PasteServiceImplTests {

  @Mock
  private PasteRepository pasteRepository;

  @Mock
  private ModelMapper modelMapper;

  @InjectMocks
  private PasteServiceImpl pasteService;

  @Test
  void shouldGetPasteByHashWithValidHash() {
    Paste paste = new Paste();
    paste.setShortLink("validHash");

    PasteDto expectedDto = new PasteDto();
    expectedDto.setShortLink("validHash");

    when(pasteRepository.findByShortLink("validHash")).thenReturn(Optional.of(paste));
    when(modelMapper.map(paste, PasteDto.class)).thenReturn(expectedDto);

    PasteDto actualDto = pasteService.getPasteByHash("validHash");

    assertEquals(expectedDto.getShortLink(), actualDto.getShortLink());
  }

  @Test
  void shouldGetPasteByHashWithInvalidHash() {
    when(pasteRepository.findByShortLink("invalidHash")).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> pasteService.getPasteByHash("invalidHash"));
  }

  @Test
  void shouldGetPublicPastes() {
    Paste paste1 = new Paste();
    Paste paste2 = new Paste();
    List<Paste> pasteList = new ArrayList<>();
    pasteList.add(paste1);
    pasteList.add(paste2);

    PasteDto dto1 = new PasteDto();
    PasteDto dto2 = new PasteDto();

    when(pasteRepository.findTop10ByPublicStatusOrderByCreationTimeDesc(PublicStatus.PUBLIC))
        .thenReturn(pasteList);
    when(modelMapper.map(paste1, PasteDto.class)).thenReturn(dto1);
    when(modelMapper.map(paste2, PasteDto.class)).thenReturn(dto2);

    List<PasteDto> pasteDtoList = pasteService.getPublicPastes();

    assertEquals(2, pasteDtoList.size());
    assertTrue(pasteDtoList.contains(dto1));
    assertTrue(pasteDtoList.contains(dto2));
  }

  @Test
  void shouldCreatePastePaste() {
    PasteDto pasteDto = new PasteDto();
    pasteDto.setContent("Test content");
    pasteDto.setCreationTime(LocalDateTime.now());
    pasteDto.setExpirationDuration(ExpirationTime._1_HOUR);
    pasteDto.setPublicStatus(PublicStatus.PUBLIC);

    when(pasteRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

    PasteResponse response = pasteService.createPaste(pasteDto);
    verify(pasteRepository, times(1)).save(any(Paste.class));

    // Verify that the returned PasteResponse contains the expected short link
    assertEquals(8, response.shortLink().length());
  }

  @Test
  void shouldRemoveExpiredPastes() {
    LocalDateTime currentTime = LocalDateTime.now();
    Paste expiredPaste1 = new Paste();
    expiredPaste1.setExpirationTime(currentTime);
    Paste expiredPaste2 = new Paste();
    expiredPaste2.setExpirationTime(currentTime);

    List<Paste> expiredPastes = new ArrayList<>();
    expiredPastes.add(expiredPaste1);
    expiredPastes.add(expiredPaste2);

    when(pasteRepository.findByExpirationTimeLessThanEqual(any(LocalDateTime.class)))
        .thenReturn(expiredPastes);

    pasteService.removeExpiredPastes();

    assertTrue(expiredPastes.isEmpty());
  }
}
