package com.example.accountapi.initializers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.accountapi.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit tests for the LoadDatabase class.
 */
@SpringBootTest
class DatabaseInitializerTest {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private DatabaseInitializer databaseInitializer;

  @BeforeEach
  public void setUp() {
    accountRepository.deleteAll();
  }

//  @Test
//  void shouldInsertTenAccountsIntoDatabase() throws Exception {
//    loadDatabase.initDatabase().run((String) null);
//    List<Account> accounts = accountRepository.findAll();
//
//    assertEquals(10, accounts.size());
//
//    for (Account account : accounts) {
//      assertNotNull(account.getAccountNumber());
//    }
//  }
}
