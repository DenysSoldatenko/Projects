package com.example.accountapi.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.accountapi.models.Account;
import com.example.accountapi.models.Amount;
import com.example.accountapi.services.AccountService;
import com.example.accountapi.utils.AccountAssembler;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Unit tests for the AccountController class.
 */
@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

  @Mock
  private AccountService accountService;

  @Mock
  private AccountAssembler accountAssembler;

  @InjectMocks
  private AccountController accountController;

  @Test
  void shouldGetAllAccounts() {
    Account account1 = new Account();
    Account account2 = new Account();
    List<Account> accountList = Arrays.asList(account1, account2);

    when(accountService.listAll()).thenReturn(accountList);

    EntityModel<Account> entityModel1 = EntityModel.of(account1);
    EntityModel<Account> entityModel2 = EntityModel.of(account2);

    when(accountAssembler.toModel(account1)).thenReturn(entityModel1);
    when(accountAssembler.toModel(account2)).thenReturn(entityModel2);

    CollectionModel<EntityModel<Account>> response = accountController.getAllAccounts();

    assertEquals(2, response.getContent().size());
  }

  @Test
  void shouldGetAccountById() {
    Account account = new Account();
    account.setId(1);

    when(accountService.findAccountById(1)).thenReturn(account);

    EntityModel<Account> entityModel = EntityModel.of(account);

    when(accountAssembler.toModel(account)).thenReturn(entityModel);

    ResponseEntity<EntityModel<Account>> response = accountController.getAccountById(1);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(account, Objects.requireNonNull(response.getBody()).getContent());
  }

  @Test
  void shouldAddAccount() {
    Account account = new Account();

    when(accountService.save(account)).thenReturn(account);

    EntityModel<Account> entityModel = EntityModel.of(account);

    when(accountAssembler.toModel(account)).thenReturn(entityModel);

    ResponseEntity<EntityModel<Account>> response = accountController.addAccount(account);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(account, Objects.requireNonNull(response.getBody()).getContent());
  }

  @Test
  void shouldUpdateAccount() {
    Account account = new Account();

    when(accountService.save(account)).thenReturn(account);

    EntityModel<Account> entityModel = EntityModel.of(account);

    when(accountAssembler.toModel(account)).thenReturn(entityModel);

    ResponseEntity<EntityModel<Account>> response = accountController.updateAccount(account);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(account, Objects.requireNonNull(response.getBody()).getContent());
  }

  @Test
  void shouldDepositToAccount() {
    Account account = new Account();
    Amount amount = new Amount(100.0f);

    when(accountService.deposit(amount.total(), 1)).thenReturn(account);

    EntityModel<Account> entityModel = EntityModel.of(account);

    when(accountAssembler.toModel(account)).thenReturn(entityModel);

    ResponseEntity<EntityModel<Account>> response = accountController.deposit(1, amount);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(account, Objects.requireNonNull(response.getBody()).getContent());
  }

  @Test
  void shouldWithdrawFromAccount() {
    Account account = new Account();
    Amount amount = new Amount(100.0f);

    when(accountService.withdraw(amount.total(), 1)).thenReturn(account);

    EntityModel<Account> entityModel = EntityModel.of(account);

    when(accountAssembler.toModel(account)).thenReturn(entityModel);

    ResponseEntity<EntityModel<Account>> response = accountController.withdraw(1, amount);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(account, Objects.requireNonNull(response.getBody()).getContent());
  }

  @Test
  void shouldDeleteAccount() {
    ResponseEntity<Account> response = accountController.deleteAccount(1);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(accountService, times(1)).delete(1);
  }
}
