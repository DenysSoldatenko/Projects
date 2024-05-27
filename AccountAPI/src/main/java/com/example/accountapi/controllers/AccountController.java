package com.example.accountapi.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.accountapi.models.Account;
import com.example.accountapi.models.Amount;
import com.example.accountapi.services.AccountService;
import com.example.accountapi.utils.AccountAssembler;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
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
 * Controller class for managing account-related HTTP endpoints.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

  private final AccountService accountService;
  private final AccountAssembler assembler;

  /**
   * Retrieves a collection of all accounts.
   *
   * @return A collection of all accounts.
   */
  @GetMapping
  public CollectionModel<EntityModel<Account>> getAllAccounts() {
    List<EntityModel<Account>> accounts = accountService.getAllAccounts().stream()
        .map(assembler::toModel)
        .toList();

    return CollectionModel.of(
    accounts,
    linkTo(methodOn(AccountController.class).getAllAccounts()).withRel("accounts")
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<Account>> getAccountById(@PathVariable("id") Integer id) {
    Account account = accountService.getAccountById(id);
    return new ResponseEntity<>(assembler.toModel(account), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<EntityModel<Account>> addAccount(@RequestBody Account account) {
    Account savedAccount = accountService.createAccount(account);
    return new ResponseEntity<>(assembler.toModel(savedAccount), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<EntityModel<Account>> updateAccount(@RequestBody Account account) {
    Account savedAccount = accountService.createAccount(account);
    return new ResponseEntity<>(assembler.toModel(savedAccount), HttpStatus.OK);
  }

  @PatchMapping("/{id}/deposit")
  public ResponseEntity<EntityModel<Account>> deposit(@PathVariable("id") Integer id,
                                                      @RequestBody Amount amount) {
    Account updatedAccount = accountService.addFunds(amount.total(), id);
    return new ResponseEntity<>(assembler.toModel(updatedAccount), HttpStatus.OK);
  }

  @PatchMapping("/{id}/withdraw")
  public ResponseEntity<EntityModel<Account>> withdraw(@PathVariable("id") Integer id,
                                                       @RequestBody Amount amount) {
    Account updatedAccount = accountService.withdrawFunds(amount.total(), id);
    return new ResponseEntity<>(assembler.toModel(updatedAccount), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Account> deleteAccount(@PathVariable("id") Integer id) {
    accountService.removeAccount(id);
    return ResponseEntity.noContent().build();
  }
}