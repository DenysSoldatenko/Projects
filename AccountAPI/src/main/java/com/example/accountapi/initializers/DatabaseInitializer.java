package com.example.accountapi.initializers;

import static java.lang.Math.round;
import static java.util.UUID.randomUUID;

import com.example.accountapi.models.Account;
import com.example.accountapi.repositories.AccountRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Configuration class for initializing the database with sample data.
 */
@Slf4j
@Component
@AllArgsConstructor
public class DatabaseInitializer {

  private final Random random = new Random();
  private final AccountRepository accountRepository;

  /**
   * Initializes the database with sample account records.
   *
   * @return A CommandLineRunner that inserts sample data into the database.
   */
  @Transactional
  public String initDatabase() {
    List<Account> accounts = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      String accountNumber = randomUUID().toString().substring(0, 8);
      float balance = (float) (round((100 + random.nextFloat() * 9900) * 100.0) / 100.0);

      Account account = Account.builder()
          .accountNumber(accountNumber)
          .balance(balance)
          .build();

      accounts.add(account);
    }

    accountRepository.saveAll(accounts);
    return "Sample database initialized with 10 random records.";
  }
}