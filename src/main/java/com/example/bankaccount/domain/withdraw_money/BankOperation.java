package com.example.bankaccount.domain.withdraw_money;

import com.example.bankaccount.domain.common.BankOperationType;

public class BankOperation {

    private final Integer id;
    private final BankOperationType bankOperationType;
    private final Float amount;

    public BankOperation(Integer id, BankOperationType bankOperationType, Float amount) {
        this.id = id;
        this.bankOperationType = bankOperationType;
        this.amount = amount;
    }

    public BankOperation(BankOperationType bankOperationType, Float amount) {
        this.id = null;
        this.bankOperationType = bankOperationType;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public Float getAmount() {
        return amount;
    }

    public BankOperationType getBankOperationType() {
        return bankOperationType;
    }
}
