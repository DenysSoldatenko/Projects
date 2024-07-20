package com.example.pastebox.controllers;

import com.example.pastebox.models.PasteDto;
import com.example.pastebox.models.PasteResponse;
import com.example.pastebox.services.PasteService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling paste-related operations.
 */
@RestController
@RequiredArgsConstructor
public class PasteController {

  private final PasteService pasteService;

  @GetMapping("/public")
  public Collection<PasteDto> getPublicPasteList() {
    return pasteService.getPublicPastes();
  }

  @GetMapping("/{hash}")
  public PasteDto getByHash(@PathVariable String hash) {
    return pasteService.getPasteByHash(hash);
  }

  @PostMapping("/")
  public PasteResponse add(@RequestBody PasteDto request) {
    return pasteService.createPaste(request);
  }
}
