package com.example.bankaccount.use_case;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.deposit_money.BankAccount;
import com.example.bankaccount.domain.deposit_money.BankAccountDao;
import com.example.bankaccount.domain.common.NotFoundBankAccount;

import java.util.Optional;

public class DepositMoney {
    private final BankAccountDao bankAccountDao;

    public DepositMoney(BankAccountDao bankAccountDao) {

        this.bankAccountDao = bankAccountDao;
    }

    public void run(AccountId accountId, Float amount) throws NotFoundBankAccount {
        final Optional<BankAccount> optionalBankAccount = bankAccountDao.find(accountId);
        if (!optionalBankAccount.isPresent()) {
            throw new NotFoundBankAccount();
        }
        final BankAccount bankAccount = optionalBankAccount.get();
        bankAccount.depositMoney(amount);
        bankAccountDao.save(bankAccount);
    }
}
