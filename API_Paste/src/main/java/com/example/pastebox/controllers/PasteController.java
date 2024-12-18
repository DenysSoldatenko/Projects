package com.example.pastebox.controllers;

import com.example.pastebox.dtos.PasteDto;
import com.example.pastebox.dtos.PasteResponse;
import com.example.pastebox.services.PasteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling paste-related operations.
 * This class exposes RESTful endpoints for creating, retrieving, and listing pastes.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/pastes")
@Tag(name = "Paste Controller", description = "Operations related to pastes")
public class PasteController {

  private final PasteService pasteService;

  /**
   * Endpoint to list all public pastes.
   *
   * @return A collection of PasteDto objects representing the public pastes.
   */
  @GetMapping("/public")
  @Operation(summary = "List all public pastes", description = "Retrieves a list of all public pastes")
  @ApiResponse(
      responseCode = "200", description = "Successfully retrieved the list of public pastes",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = PasteDto.class)
      )
  )
  public Collection<PasteDto> listPublicPastes() {
    return pasteService.getPublicPastes();
  }

  /**
   * Endpoint to retrieve a paste by its unique hash.
   *
   * @param hash The unique hash of the paste to be retrieved.
   * @return The PasteDto object corresponding to the requested paste.
   */
  @GetMapping("/{hash}")
  @Operation(summary = "Get a paste by hash", description = "Retrieves a paste based on its unique hash")
  @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200", description = "Successfully retrieved the paste",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = PasteDto.class)
        )
      ),
      @ApiResponse(responseCode = "500", description = "Paste not found",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(type = "string")
        )
      )
  })
  public PasteDto getPaste(
      @Parameter(description = "Unique hash of the paste") @PathVariable String hash
  ) {
    return pasteService.getPasteByHash(hash);
  }

  /**
   * Endpoint to create a new paste.
   *
   * @param request The PasteDto object containing the paste data to be created.
   * @return A PasteResponse object containing the details of the newly created paste.
   */
  @PostMapping
  @Operation(summary = "Create a new paste", description = "Creates a new paste entry")
  @ApiResponse(
      responseCode = "200", description = "Successfully created the paste",
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = PasteResponse.class)
      )
  )
  public PasteResponse createPaste(@RequestBody PasteDto request) {
    return pasteService.createPaste(request);
  }
}
