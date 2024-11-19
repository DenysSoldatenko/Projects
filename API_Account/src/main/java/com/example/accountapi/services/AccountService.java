package com.example.accountapi.services;

import com.example.accountapi.models.Account;
import java.util.List;

/**
 * Service interface for managing account-related operations.
 * Provides methods to perform CRUD operations and manage account balances.
 */
public interface AccountService {

  /**
   * Retrieves all accounts.
   *
   * @return a list of all {@link Account} objects.
   */
  List<Account> getAllAccounts();

  /**
   * Retrieves an account by its ID.
   *
   * @param id the ID of the account to retrieve.
   * @return the {@link Account} with the specified ID.
   */
  Account getAccountById(Integer id);

  /**
   * Creates a new account or updates an existing account.
   *
   * @param account the {@link Account} to be created or updated.
   * @return the created or updated {@link Account}.
   */
  Account createOrUpdateAccount(Account account);

  /**
   * Adds funds to an account.
   *
   * @param amount the amount of money to add to the account.
   * @param id the ID of the account to which the funds should be added.
   * @return the updated {@link Account} after the deposit.
   */
  Account addFunds(float amount, Integer id);

  /**
   * Withdraws funds from an account.
   *
   * @param amount the amount of money to withdraw from the account.
   * @param id the ID of the account to withdraw funds from.
   * @return the updated {@link Account} after the withdrawal.
   */
  Account withdrawFunds(float amount, Integer id);

  /**
   * Removes an account by its ID.
   *
   * @param id the ID of the account to be removed.
   */
  void removeAccount(Integer id);

  /**
   * Initializes account data, possibly populating the system with initial accounts or values.
   *
   * @return a message indicating the result of the initialization process.
   */
  String initializeData();
}
