package com.example.bankaccount.use_case;

import com.example.bankaccount.domain.create_account.*;

public class CreateAccount {

    private final AccountDao accountDao;
    private final IbanService ibanService;

    public CreateAccount(AccountDao accountDao, IbanService ibanService) {
        this.accountDao = accountDao;
        this.ibanService = ibanService;
    }

    public void run(UserInfo userInfo) throws InvalidUserInfoException {
        final String iban = ibanService.generate();
        AccountToSave accountToSave = AccountToSave.create(userInfo, iban);
        accountDao.save(accountToSave);
    }
}
