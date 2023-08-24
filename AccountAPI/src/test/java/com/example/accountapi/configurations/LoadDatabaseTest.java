package com.example.accountapi.configurations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.accountapi.models.Account;
import com.example.accountapi.repositories.AccountRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit tests for the LoadDatabase class.
 */
@SpringBootTest
public class LoadDatabaseTest {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private LoadDatabase loadDatabase;

  @BeforeEach
  public void setUp() {
    accountRepository.deleteAll();
  }

  @Test
  public void shouldInsertTenAccountsIntoDatabase() throws Exception {
    loadDatabase.initDatabase().run((String) null);
    List<Account> accounts = accountRepository.findAll();

    assertEquals(10, accounts.size());

    for (Account account : accounts) {
      assertNotNull(account.getAccountNumber());
    }
  }
}
