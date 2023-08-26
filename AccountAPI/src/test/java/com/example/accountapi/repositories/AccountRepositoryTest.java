package com.example.accountapi.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.accountapi.models.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit tests for the AccountRepository interface.
 */
@SpringBootTest
public class AccountRepositoryTest {

  private final AccountRepository accountRepository;

  @Autowired
  public AccountRepositoryTest(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @BeforeEach
  public void setUp() {
    accountRepository.deleteAll();
  }

  @Test
  public void depositShouldIncreaseBalance() {
    Account account = new Account();
    account.setAccountNumber("12345678");
    account.setBalance(100.0f);
    account = accountRepository.save(account);

    float depositAmount = 50.0f;
    accountRepository.deposit(depositAmount, account.getId());

    Account updatedAccount = accountRepository.findById(account.getId()).orElse(null);

    assertNotNull(updatedAccount);
    assertEquals(150.0f, updatedAccount.getBalance());
  }

  @Test
  public void withdrawShouldDecreaseBalance() {
    Account account = new Account();
    account.setAccountNumber("12345678");
    account.setBalance(100.0f);
    account = accountRepository.save(account);

    float withdrawAmount = 50.0f;
    accountRepository.withdraw(withdrawAmount, account.getId());

    Account updatedAccount = accountRepository.findById(account.getId()).orElse(null);

    assertNotNull(updatedAccount);
    assertEquals(50.0f, updatedAccount.getBalance(), 0.01);
  }
}
