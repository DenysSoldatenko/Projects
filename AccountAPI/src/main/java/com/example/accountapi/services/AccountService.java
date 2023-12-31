package com.example.accountapi.services;

import com.example.accountapi.models.Account;
import com.example.accountapi.repositories.AccountRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service class for managing account-related operations.
 */
@Service
@AllArgsConstructor
@Transactional
public class AccountService {

  private final AccountRepository accountRepository;

  public List<Account> listAll() {
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
  public Account findAccountById(Integer id) {
    return accountRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
    "Account not found with ID: " + id));
  }

  public Account save(Account account) {
    return accountRepository.save(account);
  }

  public Account deposit(float amount, Integer id) {
    accountRepository.deposit(amount, id);
    return findAccountById(id);
  }

  public Account withdraw(float amount, Integer id) {
    accountRepository.withdraw(amount, id);
    return findAccountById(id);
  }

  public void delete(Integer id) {
    accountRepository.deleteById(id);
  }
}