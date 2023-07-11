package com.example.pastebox.services;

import com.example.pastebox.models.Paste;
import com.example.pastebox.models.PasteDto;
import com.example.pastebox.models.PasteResponse;
import com.example.pastebox.repositories.PasteRepository;
import com.example.pastebox.utils.ExpirationTimeGenerator;
import com.example.pastebox.utils.LinkGenerator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

  private final PasteRepository pasteRepository;

  public final ModelMapper modelMapper;

  @Override
  public PasteDto getByHash(String hash) {
    Optional<Paste> optionalPaste = pasteRepository.findByShortLink(hash);

    if (optionalPaste.isPresent()) {
      return modelMapper.map(optionalPaste.get(), PasteDto.class);
    } else {
      throw new RuntimeException("There is no paste with this hash!");
    }
  }

  @Override
  public List<PasteDto> getPublicPastes() {
    return null;
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
}
