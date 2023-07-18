package com.example.accountapi.repositories;

import com.example.accountapi.models.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

  @Modifying
  @Transactional
  @Query("UPDATE Account a SET a.balance = a.balance + :amount WHERE a.id = :id")
  void deposit(@Param("amount") float amount, @Param("id") Integer id);

  @Modifying
  @Transactional
  @Query("UPDATE Account a SET a.balance = a.balance - :amount WHERE a.id = :id")
  void withdraw(@Param("amount") float amount, @Param("id") Integer id);
}