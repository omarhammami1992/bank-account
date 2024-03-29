package com.example.bankaccount.domain.withdraw_money;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.common.BankOperationType;
import com.example.bankaccount.domain.common.BankServiceType;
import com.example.bankaccount.domain.common.WithdrawException;

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

    public void withdrawMoney(Float amount) throws WithdrawException {
        final Float accountBudget = computeAccountBudget();
        if(accountBudget > amount) {
            bankOperations.add(new BankOperation(BankOperationType.DEBIT, BankServiceType.WITHDRAW, amount));
        } else {
            throw new WithdrawException();
        }
    }

    private Float computeAccountBudget() {
        return bankOperations.stream().map(bankOperation -> {
            if (bankOperation.getBankOperationType().equals(BankOperationType.DEBIT)) {
                return -bankOperation.getAmount();
            } else {
                return bankOperation.getAmount();
            }
        }).reduce(0f, Float::sum);
    }
}
