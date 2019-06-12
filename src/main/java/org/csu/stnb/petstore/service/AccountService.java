package org.csu.stnb.petstore.service;

import org.csu.stnb.petstore.domain.Account;

public interface AccountService {
  Account getAccount(String username);

  Account getAccount(String username, String password);

  void insertAccount(Account account);

  void updateAccount(Account account);
}
