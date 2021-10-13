package com.example.bankaccount.domain.deposit_money;

import com.example.bankaccount.common.domain.AccountId;
import com.example.bankaccount.common.domain.BankOperationType;

import java.util.List;

public class BankAccount {

    private final List<BankOperation> bankOperations;
    private final AccountId accountId;

    public BankAccount(AccountId accountId, List<BankOperation> bankOperations) {
        this.accountId = accountId;
        this.bankOperations = bankOperations;
    }

    public List<BankOperation> getBankOperations() {
        return bankOperations;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public void depositMoney(Float amount) {
        bankOperations.add(new BankOperation(BankOperationType.DEPOSIT, amount));
    }
}
