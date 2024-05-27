package com.example.accountapi.services.impl;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.example.accountapi.models.Account;
import com.example.accountapi.repositories.AccountRepository;
import java.util.List;

import com.example.accountapi.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service class for managing account-related operations.
 */
@Service
@AllArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

  private final AccountRepository repository;

  public List<Account> getAllAccounts() {
    return repository.findAll();
  }

  /**
   * Retrieves an account by its ID.
   *
   * @param id The ID of the account to retrieve.
   * @return The account with the specified ID.
   * @throws ResponseStatusException If the account is not found,
   *     a NOT_FOUND response status exception is thrown.
   */
  public Account getAccountById(Integer id) {
    return repository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Account not found with ID: " + id));
  }

  public Account createAccount(Account account) {
    return repository.save(account);
  }

  public Account addFunds(float amount, Integer id) {
    repository.deposit(amount, id);
    return getAccountById(id);
  }

  public Account withdrawFunds(float amount, Integer id) {
    repository.withdraw(amount, id);
    return getAccountById(id);
  }

  public void removeAccount(Integer id) {
    repository.deleteById(id);
  }
}
