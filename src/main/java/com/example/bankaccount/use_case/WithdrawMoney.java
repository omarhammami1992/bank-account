package com.example.bankaccount.use_case;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.common.NotFoundBankAccount;
import com.example.bankaccount.domain.common.WithdrawException;
import com.example.bankaccount.domain.withdraw_money.BankAccount;
import com.example.bankaccount.domain.withdraw_money.BankAccountDao;

import java.util.Optional;

public class WithdrawMoney {

    private final BankAccountDao bankAccountDao;

    public WithdrawMoney(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    public void run(AccountId accountId, Float amount) throws WithdrawException, NotFoundBankAccount {
        final Optional<BankAccount> optionalBankAccount = bankAccountDao.find(accountId);
        if (!optionalBankAccount.isPresent()) {
            throw new NotFoundBankAccount();
        }
        final BankAccount bankAccount = optionalBankAccount.get();
        bankAccount.withdrawMoney(amount);
        bankAccountDao.save(bankAccount);
    }
}
