package com.example.bankaccount.use_case;

import com.example.bankaccount.domain.AccountDao;
import com.example.bankaccount.domain.AccountToSave;
import com.example.bankaccount.domain.InvalidUserInfoException;
import com.example.bankaccount.domain.UserInfo;

public class CreateAccount {

    private final AccountDao accountDao;

    public CreateAccount(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void run(UserInfo userInfo) throws InvalidUserInfoException {
        AccountToSave accountToSave = AccountToSave.create(userInfo);
        accountDao.save(accountToSave);
    }
}
