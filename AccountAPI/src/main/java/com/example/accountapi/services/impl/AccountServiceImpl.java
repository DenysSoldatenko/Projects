package com.example.accountapi.services.impl;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.example.accountapi.initializers.DatabaseInitializer;
import com.example.accountapi.models.Account;
import com.example.accountapi.repositories.AccountRepository;
import com.example.accountapi.services.AccountService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final DatabaseInitializer databaseInitializer;

  @Override
  public List<Account> getAllAccounts() {
    return accountRepository.findAll();
  }

  /**
   * Retrieves an account by its ID.
   *
   * @param id The ID of the account to retrieve.
   * @return The account with the specified ID.
   * @throws ResponseStatusException If the account is not found,
   *     a NOT_FOUND response status exception is thrown.
   */
  @Override
  public Account getAccountById(Integer id) {
    return accountRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Account not found with ID: " + id));
  }

  @Override
  public Account createOrUpdateAccount(Account account) {
    return accountRepository.save(account);
  }

  @Override
  public Account addFunds(float amount, Integer id) {
    accountRepository.deposit(amount, id);
    return getAccountById(id);
  }

  @Override
  public Account withdrawFunds(float amount, Integer id) {
    accountRepository.withdraw(amount, id);
    return getAccountById(id);
  }

  @Override
  public void removeAccount(Integer id) {
    accountRepository.deleteById(id);
  }

  @Override
  public String initializeData() {
    return databaseInitializer.initDatabase();
  }
}
