package org.csu.stnb.petstore.service.impl;

import org.csu.stnb.petstore.domain.Account;
import org.csu.stnb.petstore.persistence.AccountMapper;
import org.csu.stnb.petstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account getAccount(String username) {
        return accountMapper.getAccountByUsername(username);
    }

    @Override
    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountMapper.getAccountByUsernameAndPassword(account);
    }

    @Override
    public void insertAccount(Account account) {
        accountMapper.insertAccount(account);
        accountMapper.insertProfile(account);
        accountMapper.insertSignon(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountMapper.updateAccount(account);
        accountMapper.updateProfile(account);

        if (account.getPassword() != null && account.getPassword().length() > 0) {
            accountMapper.updateSignon(account);
        }
    }
}
