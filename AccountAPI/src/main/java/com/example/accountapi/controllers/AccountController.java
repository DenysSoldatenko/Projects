package com.example.accountapi.controllers;

import com.example.accountapi.models.Account;
import com.example.accountapi.services.AccountService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<List<Account>> getAllAccounts() {
    List<Account> list = accountService.listAll();

    if (list.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public Account getAccountById(@PathVariable("id") Integer id) {
    return accountService.findAccountById(id);
  }
}