package com.example.bankaccount.use_case;

import com.example.bankaccount.common.domain.AccountId;
import com.example.bankaccount.common.domain.Iban;
import com.example.bankaccount.domain.transfer_money.BankAccount;
import com.example.bankaccount.domain.transfer_money.BankAccountDao;
import com.example.bankaccount.common.domain.WithdrawException;

import java.util.Optional;

public class TransferMoney {

    private final BankAccountDao bankAccountDao;

    public TransferMoney(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    public void run(AccountId payerAccountId, Iban iban, Float amount) throws WithdrawException {
        final Optional<BankAccount> optionalBankAccount = bankAccountDao.findById(payerAccountId);
        if (optionalBankAccount.isPresent()) {
            final BankAccount payerBankAccount = optionalBankAccount.get();
            payerBankAccount.withdrawMoney(amount);
            bankAccountDao.save(payerBankAccount);
        }
    }
}
