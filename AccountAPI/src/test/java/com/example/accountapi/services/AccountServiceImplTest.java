package com.example.accountapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.accountapi.models.Account;
import com.example.accountapi.repositories.AccountRepository;
import java.util.List;
import java.util.Optional;

import com.example.accountapi.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

/**
 * Unit tests for the AccountServiceImpl class.
 */
@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

  @Mock
  private AccountRepository accountRepository;

  @InjectMocks
  private AccountServiceImpl accountServiceImpl;

  @BeforeEach
  public void setUp() {
    accountRepository.deleteAll();
  }

  @Test
  void shouldRetrieveAllAccounts() {
    Account account1 = new Account();
    Account account2 = new Account();
    when(accountRepository.findAll()).thenReturn(List.of(account1, account2));

    List<Account> accounts = accountServiceImpl.getAllAccounts();

    assertEquals(2, accounts.size());
    assertEquals(account1, accounts.get(0));
    assertEquals(account2, accounts.get(1));
  }

  @Test
  void shouldFindAccountById() {
    Account account = new Account();
    account.setId(1);

    when(accountRepository.findById(1)).thenReturn(Optional.of(account));

    Account foundAccount = accountServiceImpl.getAccountById(1);

    assertEquals(account, foundAccount);
  }

  @Test
  void shouldThrowExceptionWhenAccountNotFound() {
    when(accountRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(ResponseStatusException.class, () -> accountServiceImpl.getAccountById(1));
  }

  @Test
  void shouldSaveAccount() {
    Account account = new Account();

    when(accountRepository.save(account)).thenReturn(account);

    Account savedAccount = accountServiceImpl.createOrUpdateAccount(account);

    assertEquals(account, savedAccount);
  }

  @Test
  void shouldWithdrawFromAccount() {
    // Prepare test data
    Account account = new Account();
    account.setId(1);
    account.setBalance(100.0f);

    // Mock the findById behavior to return the account
    when(accountRepository.findById(1)).thenReturn(Optional.of(account));

    // Define behavior for accountRepository.withdraw to deduct the amount
    doAnswer(invocation -> {
      float amountToWithdraw = invocation.getArgument(0);
      account.setBalance(account.getBalance() - amountToWithdraw);
      return null;
    }).when(accountRepository).withdraw(100.0f, 1);

    // Call the withdrawal method
    Account updatedAccount = accountServiceImpl.withdrawFunds(100.0f, 1);

    assertEquals(account, updatedAccount);
    assertEquals(0.0f, updatedAccount.getBalance());
  }


  @Test
  void shouldDepositToAccount() {
    // Prepare test data
    Account account = new Account();
    account.setId(1);
    account.setBalance(100.0f);

    // Mock the findById behavior to return the account
    when(accountRepository.findById(1)).thenReturn(Optional.of(account));

    // Define behavior for accountRepository.save to update the balance
    doAnswer(invocation -> {
      float amountToDeposit = invocation.getArgument(0);
      account.setBalance(account.getBalance() + amountToDeposit);
      return null;
    }).when(accountRepository).deposit(100.0f, 1);

    // Call the deposit method
    Account updatedAccount = accountServiceImpl.addFunds(100.0f, 1);

    // Assert the result
    assertEquals(account, updatedAccount);
    assertEquals(200.0f, updatedAccount.getBalance());
  }

  @Test
  void shouldDeleteAccount() {
    Account account = new Account();
    account.setId(1);

    accountServiceImpl.removeAccount(1);

    verify(accountRepository, times(1)).deleteById(1);
  }
}
