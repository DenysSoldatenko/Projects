package com.example.pastebox.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.pastebox.models.PasteDto;
import com.example.pastebox.models.PasteResponse;
import com.example.pastebox.services.PasteService;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for the PasteController class.
 */
@ExtendWith(MockitoExtension.class)
class PasteControllerTest {

  @Mock
  private PasteService pasteService;

  @InjectMocks
  private PasteController pasteController;

  @Test
  void shouldGetPublicPasteList() {
    PasteDto paste1 = new PasteDto();
    PasteDto paste2 = new PasteDto();
    List<PasteDto> pasteList = Arrays.asList(paste1, paste2);

    when(pasteService.getPublicPastes()).thenReturn(pasteList);

    Collection<PasteDto> response = pasteController.getPublicPasteList();

    assertEquals(2, response.size());
  }

  @Test
  void shouldGetPasteByHash() {
    PasteDto paste = new PasteDto();
    paste.setShortLink("abcd1234");

    when(pasteService.getPasteByHash("abcd1234")).thenReturn(paste);

    PasteDto response = pasteController.getByHash("abcd1234");

    assertEquals("abcd1234", response.getShortLink());
  }

  @Test
  void shouldAddPaste() {
    PasteDto pasteDto = new PasteDto();
    PasteResponse pasteResponse = new PasteResponse("xyz7890");

    when(pasteService.createPaste(pasteDto)).thenReturn(pasteResponse);

    PasteResponse response = pasteController.add(pasteDto);

    assertEquals("xyz7890", response.shortLink());
  }
}
