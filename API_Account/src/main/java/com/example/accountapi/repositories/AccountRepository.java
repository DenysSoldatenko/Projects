package com.example.accountapi.repositories;

import com.example.accountapi.models.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Account entities in the database.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

  /**
   * Deposits a specified amount into an account by updating the account's balance.
   *
   * @param amount the amount to be deposited into the account.
   * @param id the ID of the account to deposit the amount into.
   */
  @Modifying
  @Transactional
  @Query("UPDATE Account a SET a.balance = a.balance + :amount WHERE a.id = :id")
  void deposit(@Param("amount") float amount, @Param("id") Integer id);

  /**
   * Withdraws a specified amount from an account by updating the account's balance.
   *
   * @param amount the amount to be withdrawn from the account.
   * @param id the ID of the account to withdraw the amount from.
   */
  @Modifying
  @Transactional
  @Query("UPDATE Account a SET a.balance = a.balance - :amount WHERE a.id = :id")
  void withdraw(@Param("amount") float amount, @Param("id") Integer id);
}