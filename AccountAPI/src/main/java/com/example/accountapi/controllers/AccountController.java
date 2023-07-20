package com.example.accountapi.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.accountapi.models.Account;
import com.example.accountapi.models.Amount;
import com.example.accountapi.services.AccountService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
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

@RestController
@AllArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

  private final AccountService accountService;

  @GetMapping("/")
  public CollectionModel<Account> getAllAccounts() {
    List<Account> list = accountService.listAll();

    for (Account account : list) {
      account.add(linkTo(
          methodOn(AccountController.class).getAccountById(account.getId())
      ).withSelfRel());

      account.add(linkTo(
          methodOn(AccountController.class).deposit(account.getId(), null)
      ).withRel("deposit"));

      account.add(linkTo(
          methodOn(AccountController.class).withdraw(account.getId(), null)
      ).withRel("withdraw"));

      account.add(linkTo(
          methodOn(AccountController.class).getAllAccounts()
      ).withRel(IanaLinkRelations.COLLECTION));
    }

    CollectionModel<Account> collectionModel = CollectionModel.of(list);

    collectionModel.add(linkTo(
        methodOn(AccountController.class).getAllAccounts()
    ).withSelfRel());

    return collectionModel;
  }

  @GetMapping("/{id}")
  public Account getAccountById(@PathVariable("id") Integer id) {
    Account account = accountService.findAccountById(id);

    account.add(linkTo(
        methodOn(AccountController.class).getAccountById(account.getId())
    ).withSelfRel());

    account.add(linkTo(
        methodOn(AccountController.class).deposit(account.getId(), null)
    ).withRel("deposit"));

    account.add(linkTo(
        methodOn(AccountController.class).withdraw(account.getId(), null)
    ).withRel("withdraw"));

    account.add(linkTo(
        methodOn(AccountController.class).getAllAccounts()
    ).withRel(IanaLinkRelations.COLLECTION));

    return account;
  }

  @PostMapping
  public ResponseEntity<Account> createAccount(@RequestBody Account account) {
    Account savedAccount = accountService.save(account);

    account.add(linkTo(
        methodOn(AccountController.class).getAccountById(savedAccount.getId())
    ).withSelfRel());

    account.add(linkTo(
        methodOn(AccountController.class).deposit(account.getId(), null)
    ).withRel("deposit"));

    savedAccount.add(linkTo(
        methodOn(AccountController.class).withdraw(savedAccount.getId(), null)
    ).withRel("withdraw"));

    account.add(linkTo(
        methodOn(AccountController.class).getAllAccounts()
    ).withRel(IanaLinkRelations.COLLECTION));

    return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
    Account updatedAccount = accountService.save(account);

    updatedAccount.add(linkTo(
        methodOn(AccountController.class).getAccountById(updatedAccount.getId())
    ).withSelfRel());

    updatedAccount.add(linkTo(
        methodOn(AccountController.class).deposit(updatedAccount.getId(), null)
    ).withRel("deposit"));

    updatedAccount.add(linkTo(
        methodOn(AccountController.class).withdraw(updatedAccount.getId(), null)
    ).withRel("withdraw"));

    updatedAccount.add(linkTo(
        methodOn(AccountController.class).getAllAccounts()
    ).withRel(IanaLinkRelations.COLLECTION));

    return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
  }

  @PatchMapping("/{id}/deposit")
  public ResponseEntity<Account> deposit(@PathVariable("id") Integer id,
                                         @RequestBody Amount amount) {

    Account updatedAccount = accountService.deposit(amount.total(), id);

    updatedAccount.add(linkTo(
        methodOn(AccountController.class).getAccountById(updatedAccount.getId())
    ).withSelfRel());

    updatedAccount.add(linkTo(
        methodOn(AccountController.class).deposit(updatedAccount.getId(), null)
    ).withRel("deposit"));

    updatedAccount.add(linkTo(
        methodOn(AccountController.class).withdraw(updatedAccount.getId(), null)
    ).withRel("withdraw"));

    updatedAccount.add(linkTo(
        methodOn(AccountController.class).getAllAccounts()
    ).withRel(IanaLinkRelations.COLLECTION));

    return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
  }

  @PatchMapping("/{id}/withdraw")
  public ResponseEntity<Account> withdraw(@PathVariable("id") Integer id,
                                          @RequestBody Amount amount) {
    Account updatedAccount = accountService.withdraw(amount.total(), id);

    updatedAccount.add(linkTo(
        methodOn(AccountController.class).getAccountById(updatedAccount.getId())
    ).withSelfRel());

    updatedAccount.add(linkTo(
        methodOn(AccountController.class).deposit(updatedAccount.getId(), null)
    ).withRel("deposit"));

    updatedAccount.add(linkTo(
        methodOn(AccountController.class).withdraw(updatedAccount.getId(), null)
    ).withRel("withdraw"));

    updatedAccount.add(linkTo(
        methodOn(AccountController.class).getAllAccounts()
    ).withRel(IanaLinkRelations.COLLECTION));

    return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Account> deleteAccount(@PathVariable("id") Integer id) {
    accountService.delete(id);
    return ResponseEntity.noContent().build();
  }
}