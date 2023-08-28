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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

/**
 * Unit tests for the AccountService class.
 */
@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

  @Mock
  private AccountRepository accountRepository;

  @InjectMocks
  private AccountService accountService;

  @BeforeEach
  public void setUp() {
    accountRepository.deleteAll();
  }

  @Test
  public void shouldRetrieveAllAccounts() {
    Account account1 = new Account();
    Account account2 = new Account();
    when(accountRepository.findAll()).thenReturn(List.of(account1, account2));

    List<Account> accounts = accountService.listAll();

    assertEquals(2, accounts.size());
    assertEquals(account1, accounts.get(0));
    assertEquals(account2, accounts.get(1));
  }

  @Test
  public void shouldFindAccountById() {
    Account account = new Account();
    account.setId(1);

    when(accountRepository.findById(1)).thenReturn(Optional.of(account));

    Account foundAccount = accountService.findAccountById(1);

    assertEquals(account, foundAccount);
  }

  @Test
  public void shouldThrowExceptionWhenAccountNotFound() {
    when(accountRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(ResponseStatusException.class, () -> accountService.findAccountById(1));
  }

  @Test
  public void shouldSaveAccount() {
    Account account = new Account();

    when(accountRepository.save(account)).thenReturn(account);

    Account savedAccount = accountService.save(account);

    assertEquals(account, savedAccount);
  }

  @Test
  public void shouldWithdrawFromAccount() {
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
    Account updatedAccount = accountService.withdraw(100.0f, 1);

    assertEquals(account, updatedAccount);
    assertEquals(0.0f, updatedAccount.getBalance());
  }


  @Test
  public void shouldDepositToAccount() {
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
    Account updatedAccount = accountService.deposit(100.0f, 1);

    // Assert the result
    assertEquals(account, updatedAccount);
    assertEquals(200.0f, updatedAccount.getBalance());
  }

  @Test
  public void shouldDeleteAccount() {
    Account account = new Account();
    account.setId(1);

    accountService.delete(1);

    verify(accountRepository, times(1)).deleteById(1);
  }
}
