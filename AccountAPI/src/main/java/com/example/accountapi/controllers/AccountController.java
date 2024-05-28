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
public class AccountController {

  private final AccountService accountService;
  private final AccountAssembler accountAssembler;

  @PostMapping("/initialize")
  public ResponseEntity<String> initializeData() {
    return new ResponseEntity<>(accountService.initializeData(), CREATED);
  }

  /**
   * Retrieves a collection of all accounts.
   *
   * @return A collection of all accounts.
   */
  @GetMapping
  public CollectionModel<EntityModel<Account>> getAllAccounts() {
    List<EntityModel<Account>> accounts = accountService.getAllAccounts().stream()
        .map(accountAssembler::toModel)
        .toList();

    return of(accounts, linkTo(methodOn(AccountController.class).getAllAccounts()).withRel("accounts"));
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<Account>> getAccountById(@PathVariable("id") Integer id) {
    Account account = accountService.getAccountById(id);
    return new ResponseEntity<>(accountAssembler.toModel(account), OK);
  }

  @PostMapping
  public ResponseEntity<EntityModel<Account>> addAccount(@RequestBody Account account) {
    Account savedAccount = accountService.createOrUpdateAccount(account);
    return new ResponseEntity<>(accountAssembler.toModel(savedAccount), CREATED);
  }

  @PutMapping
  public ResponseEntity<EntityModel<Account>> updateAccount(@RequestBody Account account) {
    Account updatedAccount = accountService.createOrUpdateAccount(account);
    return new ResponseEntity<>(accountAssembler.toModel(updatedAccount), OK);
  }

  @PatchMapping("/{id}/deposit")
  public ResponseEntity<EntityModel<Account>> depositFunds(@PathVariable("id") Integer id,
                                                           @RequestBody Amount amount) {
    Account updatedAccount = accountService.addFunds(amount.total(), id);
    return new ResponseEntity<>(accountAssembler.toModel(updatedAccount), OK);
  }

  @PatchMapping("/{id}/withdraw")
  public ResponseEntity<EntityModel<Account>> withdrawFunds(@PathVariable("id") Integer id,
                                                            @RequestBody Amount amount) {
    Account updatedAccount = accountService.withdrawFunds(amount.total(), id);
    return new ResponseEntity<>(accountAssembler.toModel(updatedAccount), OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Account> deleteAccount(@PathVariable("id") Integer id) {
    accountService.removeAccount(id);
    return noContent().build();
  }
}
