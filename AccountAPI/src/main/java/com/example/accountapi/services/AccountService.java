package com.example.accountapi.services;

import com.example.accountapi.models.Account;
import java.util.List;

/**
 * Service class for managing account-related operations.
 */
public interface AccountService {

  List<Account> getAllAccounts();

  Account getAccountById(Integer id);

  Account createOrUpdateAccount(Account account);

  Account addFunds(float amount, Integer id);

  Account withdrawFunds(float amount, Integer id);

  void removeAccount(Integer id);

  String initializeData();
}
