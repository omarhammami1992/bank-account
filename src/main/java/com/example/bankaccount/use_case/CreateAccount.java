package com.example.bankaccount.use_case;

import com.example.bankaccount.common.domain.Iban;
import com.example.bankaccount.domain.create_account.*;

public class CreateAccount {

    private final AccountDao accountDao;
    private final IbanService ibanService;

    public CreateAccount(AccountDao accountDao, IbanService ibanService) {
        this.accountDao = accountDao;
        this.ibanService = ibanService;
    }

    public void run(UserInfo userInfo) throws InvalidUserInfoException {
        final Iban iban = ibanService.generate();
        AccountToSave accountToSave = new AccountToSave(userInfo, iban);
        accountDao.save(accountToSave);
    }
}
