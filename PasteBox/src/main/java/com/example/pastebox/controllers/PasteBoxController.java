package com.example.pastebox.controllers;

import com.example.pastebox.models.PasteDto;
import com.example.pastebox.models.PasteResponse;
import com.example.pastebox.services.PostService;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PasteBoxController {

  private final PostService postService;

  @GetMapping("/")
  public Collection<String> getPublicPasteList() {
    return Collections.emptyList();
  }

  @GetMapping("/{hash}")
  public PasteDto getByHash(@PathVariable String hash) {
    return postService.getByHash(hash);
  }

  @PostMapping("/")
  public PasteResponse add(@RequestBody PasteDto request) {
    return postService.add(request);
  }
}
