package com.example.accountapi.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Account class.
 */
public class AccountTest {
  private Account account;

  @BeforeEach
  public void setUp() {
    account = new Account();
  }

  @Test
  public void testIdGetterAndSetter() {
    account.setId(1);
    assertEquals(1, account.getId());
  }

  @Test
  public void testAccountNumberGetterAndSetter() {
    account.setAccountNumber("1234567890");
    assertEquals("1234567890", account.getAccountNumber());
  }

  @Test
  public void testBalanceGetterAndSetter() {
    account.setBalance(100.0f);
    assertEquals(100.0f, account.getBalance());
  }

  @Test
  public void testEqualsAndHashCode() {
    Account account1 = new Account();
    account1.setId(1);

    Account account2 = new Account();
    account2.setId(1);

    assertEquals(account1, account2);
    assertEquals(account1.hashCode(), account2.hashCode());
  }

  @Test
  public void testBuilder() {
    Account account = Account.builder()
        .id(1)
        .accountNumber("1234567890")
        .balance(100.0f)
        .build();

    assertEquals(1, account.getId());
    assertEquals("1234567890", account.getAccountNumber());
    assertEquals(100.0f, account.getBalance(), 0.01);
  }
}
