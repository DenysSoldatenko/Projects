package com.example.accountapi.configurations;

import com.example.accountapi.models.Account;
import com.example.accountapi.repositories.AccountRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

/*@AllArgsConstructor
@Configuration*/
public class LoadDatabase {

  private AccountRepository accountRepository;

  @Bean
  public CommandLineRunner initDatabase() {
    return args -> {
      List<Account> accounts = new ArrayList<>();
      Random random = new Random();

      for (int i = 0; i < 10; i++) {
        String accountNumber = UUID.randomUUID().toString().substring(0, 8);
        float balance = (float) (Math.round((100 + random.nextFloat() * 9900) * 100.0) / 100.0);

        Account account = Account.builder()
            .accountNumber(accountNumber)
            .balance(balance)
            .build();

        accounts.add(account);
      }

      accountRepository.saveAll(accounts);
      System.out.println("Sample database initialized with 10 random records.");
    };
  }

}