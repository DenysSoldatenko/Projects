package com.example.accountapi.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.accountapi.models.Account;
import com.example.accountapi.services.AccountService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        methodOn(AccountController.class).getAllAccounts()
    ).withRel(IanaLinkRelations.COLLECTION));

    return account;
  }
}