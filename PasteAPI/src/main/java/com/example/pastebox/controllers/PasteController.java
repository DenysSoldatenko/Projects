package com.example.pastebox.controllers;

import com.example.pastebox.models.PasteDto;
import com.example.pastebox.models.PasteResponse;
import com.example.pastebox.services.PasteService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling paste-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/pastes")
public class PasteController {

  private final PasteService pasteService;

  @GetMapping("/public")
  public Collection<PasteDto> listPublicPastes() {
    return pasteService.getPublicPastes();
  }

  @GetMapping("/{hash}")
  public PasteDto getPaste(@PathVariable String hash) {
    return pasteService.getPasteByHash(hash);
  }

  @PostMapping
  public PasteResponse createPaste(@RequestBody PasteDto request) {
    return pasteService.createPaste(request);
  }
}
