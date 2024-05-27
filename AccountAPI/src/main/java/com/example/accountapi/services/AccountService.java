package com.example.accountapi.services;

import com.example.accountapi.models.Account;
import java.util.List;

public interface AccountService {

  List<Account> getAllAccounts();

  Account getAccountById(Integer id);

  Account createAccount(Account account);

  Account addFunds(float amount, Integer id);

  Account withdrawFunds(float amount, Integer id);

  void removeAccount(Integer id);
}
