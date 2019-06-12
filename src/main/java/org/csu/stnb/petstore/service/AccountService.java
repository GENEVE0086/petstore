package org.csu.stnb.petstore.service;

import org.csu.stnb.petstore.domain.Account;

public interface AccountService {
    public Account getAccount(String username);

    public Account getAccount(String username, String password);

    public void insertAccount(Account account);

    public void updateAccount(Account account);
}
