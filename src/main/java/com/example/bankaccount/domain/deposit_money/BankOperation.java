package com.example.bankaccount.domain.deposit_money;

import com.example.bankaccount.domain.common.BankOperationType;
import com.example.bankaccount.domain.common.BankServiceType;

public class BankOperation {

    private final Integer id;
    private final BankOperationType bankOperationType;
    private final BankServiceType bankServiceType;
    private final Float amount;

    public BankOperation(Integer id, BankOperationType bankOperationType, BankServiceType bankServiceType, Float amount) {
        this.id = id;
        this.bankOperationType = bankOperationType;
        this.bankServiceType = bankServiceType;
        this.amount = amount;
    }

    public BankOperation(BankOperationType bankOperationType, BankServiceType bankServiceType, Float amount) {
        this.id = null;
        this.bankOperationType = bankOperationType;
        this.bankServiceType = bankServiceType;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public BankOperationType getBankOperationType() {
        return bankOperationType;
    }

    public BankServiceType getBankServiceType() {
        return bankServiceType;
    }

    public Float getAmount() {
        return amount;
    }
}
