package com.example.accountapi.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.accountapi.controllers.AccountController;
import com.example.accountapi.models.Account;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class AccountAssembler
    implements RepresentationModelAssembler<Account, EntityModel<Account>> {

  @Override
  public @NonNull EntityModel<Account> toModel(@NonNull Account entity) {
    EntityModel<Account> accountModel = EntityModel.of(entity);

    accountModel.add(createSelfLink(entity));
    accountModel.add(createDepositLink(entity));
    accountModel.add(createWithdrawLink(entity));
    accountModel.add(createCollectionLink());

    return accountModel;
  }

  private Link createSelfLink(Account entity) {
    return linkTo(methodOn(AccountController.class).getAccountById(entity.getId())).withSelfRel();
  }

  private Link createDepositLink(Account entity) {
    return linkTo(methodOn(AccountController.class).deposit(entity.getId(), null))
    .withRel("deposit");
  }

  private Link createWithdrawLink(Account entity) {
    return linkTo(methodOn(AccountController.class).withdraw(entity.getId(), null))
    .withRel("withdraw");
  }

  private Link createCollectionLink() {
    return linkTo(methodOn(AccountController.class).getAllAccounts())
    .withRel(IanaLinkRelations.COLLECTION);
  }
}
