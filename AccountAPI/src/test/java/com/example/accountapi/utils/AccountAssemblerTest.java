package com.example.accountapi.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.accountapi.models.Account;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

/**
 * Unit tests for the AccountAssembler class.
 */
public class AccountAssemblerTest {

  private AccountAssembler accountAssembler;

  @BeforeEach
  public void setUp() {
    accountAssembler = new AccountAssembler();
  }

  @Test
  public void shouldCreateEntityModelWithLinksForAccount() {
    Account account = new Account();
    account.setId(1);
    account.setAccountNumber("12345678");
    account.setBalance(100.0f);

    EntityModel<Account> accountModel = accountAssembler.toModel(account);

    assertNotNull(accountModel);

    Optional<Link> selfLink = accountModel.getLink("self");
    assertNotNull(selfLink);
    assertTrue(selfLink.get().getHref().contains("/accounts/1"));

    Optional<Link> depositLink = accountModel.getLink("deposit");
    assertNotNull(depositLink);
    assertTrue(depositLink.get().getHref().contains("/accounts/1/deposit"));

    Optional<Link> withdrawLink = accountModel.getLink("withdraw");
    assertNotNull(withdrawLink);
    assertTrue(withdrawLink.get().getHref().contains("/accounts/1/withdraw"));

    Optional<Link> collectionLink = accountModel.getLink("collection");
    assertNotNull(collectionLink);
    assertTrue(collectionLink.get().getHref().contains("/accounts"));
  }
}
