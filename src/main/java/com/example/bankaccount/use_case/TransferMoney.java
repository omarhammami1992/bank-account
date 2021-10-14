package com.example.bankaccount.use_case;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.common.Iban;
import com.example.bankaccount.domain.common.NotFoundBankAccount;
import com.example.bankaccount.domain.transfer_money.BankAccount;
import com.example.bankaccount.domain.transfer_money.BankAccountDao;
import com.example.bankaccount.domain.common.WithdrawException;

import java.util.Optional;

public class TransferMoney {

    private final BankAccountDao bankAccountDao;

    public TransferMoney(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    public void run(AccountId payerAccountId, Iban iban, Float amount) throws WithdrawException, NotFoundBankAccount {
        final Optional<BankAccount> optionalPayerBankAccount = bankAccountDao.findById(payerAccountId);
        final Optional<BankAccount> optionalPayeeBankAccount = bankAccountDao.findByIban(iban);
        if (!optionalPayerBankAccount.isPresent() || !optionalPayeeBankAccount.isPresent()) {
            throw new NotFoundBankAccount();
        }
        final BankAccount payerBankAccount = optionalPayerBankAccount.get();
        final BankAccount payeeBankAccount = optionalPayeeBankAccount.get();
        payerBankAccount.withdrawMoney(amount);
        payeeBankAccount.depositMoney(amount);
        bankAccountDao.save(payerBankAccount);
        bankAccountDao.save(payeeBankAccount);
    }
}
