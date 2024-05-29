package com.example.accountapi.controllers;

import static org.springframework.hateoas.CollectionModel.of;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.noContent;

import com.example.accountapi.models.Account;
import com.example.accountapi.models.Amount;
import com.example.accountapi.services.AccountService;
import com.example.accountapi.utils.AccountAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing account-related operations.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/accounts")
@Tag(name = "Account Management", description = "Operations related to accounts")
public class AccountController {

  private final AccountService accountService;
  private final AccountAssembler accountAssembler;

  @PostMapping("/initialize")
  @Operation(summary = "Initialize account data", description = "Initializes the data for accounts")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Data initialized successfully"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public ResponseEntity<String> initializeData() {
    return new ResponseEntity<>(accountService.initializeData(), CREATED);
  }

  @GetMapping
  @Operation(summary = "Get all accounts", description = "Retrieves a collection of all accounts")
  @ApiResponses({
      @ApiResponse(
        responseCode = "200", description = "Accounts retrieved successfully",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = Account.class)
        )
      ),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public CollectionModel<EntityModel<Account>> getAllAccounts() {
    List<EntityModel<Account>> accounts = accountService.getAllAccounts().stream()
        .map(accountAssembler::toModel)
        .toList();

    return of(accounts, linkTo(methodOn(AccountController.class).getAllAccounts()).withRel("accounts"));
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get account by ID", description = "Retrieves an account by its ID")
  @ApiResponses({
      @ApiResponse(
        responseCode = "200", description = "Account retrieved successfully",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = Account.class)
        )
      ),
      @ApiResponse(responseCode = "404", description = "Account not found"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public ResponseEntity<EntityModel<Account>> getAccountById(@PathVariable("id") Integer id) {
    Account account = accountService.getAccountById(id);
    return new ResponseEntity<>(accountAssembler.toModel(account), OK);
  }

  @PostMapping
  @Operation(summary = "Add a new account", description = "Creates a new account")
  @ApiResponses({
      @ApiResponse(
        responseCode = "201", description = "Account created successfully",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = Account.class)
        )
      ),
      @ApiResponse(responseCode = "400", description = "Invalid account data"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public ResponseEntity<EntityModel<Account>> addAccount(@RequestBody Account account) {
    Account savedAccount = accountService.createOrUpdateAccount(account);
    return new ResponseEntity<>(accountAssembler.toModel(savedAccount), CREATED);
  }

  @PutMapping
  @Operation(summary = "Update an existing account", description = "Updates an existing account")
  @ApiResponses({
      @ApiResponse(
        responseCode = "200", description = "Account updated successfully",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = Account.class)
        )
      ),
      @ApiResponse(responseCode = "400", description = "Invalid account data"),
      @ApiResponse(responseCode = "404", description = "Account not found"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public ResponseEntity<EntityModel<Account>> updateAccount(@RequestBody Account account) {
    Account updatedAccount = accountService.createOrUpdateAccount(account);
    return new ResponseEntity<>(accountAssembler.toModel(updatedAccount), OK);
  }

  @PatchMapping("/{id}/deposit")
  @Operation(summary = "Deposit funds into an account", description = "Deposits funds into an account")
  @ApiResponses({
      @ApiResponse(
        responseCode = "200", description = "Funds deposited successfully",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = Account.class)
        )
      ),
      @ApiResponse(responseCode = "400", description = "Invalid amount data"),
      @ApiResponse(responseCode = "404", description = "Account not found"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public ResponseEntity<EntityModel<Account>> depositFunds(@PathVariable("id") Integer id,
                                                           @RequestBody Amount amount) {
    Account updatedAccount = accountService.addFunds(amount.total(), id);
    return new ResponseEntity<>(accountAssembler.toModel(updatedAccount), OK);
  }

  @PatchMapping("/{id}/withdraw")
  @Operation(summary = "Withdraw funds from an account", description = "Withdraws funds from an account")
  @ApiResponses({
      @ApiResponse(
        responseCode = "200", description = "Funds withdrawn successfully",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = Account.class)
        )
      ),
      @ApiResponse(responseCode = "400", description = "Invalid amount data"),
      @ApiResponse(responseCode = "404", description = "Account not found"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public ResponseEntity<EntityModel<Account>> withdrawFunds(@PathVariable("id") Integer id,
                                                            @RequestBody Amount amount) {
    Account updatedAccount = accountService.withdrawFunds(amount.total(), id);
    return new ResponseEntity<>(accountAssembler.toModel(updatedAccount), OK);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete an account", description = "Deletes an account by its ID")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = "Account deleted successfully"),
    @ApiResponse(responseCode = "404", description = "Account not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public ResponseEntity<Account> deleteAccount(@PathVariable("id") Integer id) {
    accountService.removeAccount(id);
    return noContent().build();
  }
}
