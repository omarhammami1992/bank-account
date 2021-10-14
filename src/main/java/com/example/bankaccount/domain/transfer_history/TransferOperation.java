package com.example.bankaccount.domain.transfer_history;

import com.example.bankaccount.domain.common.BankOperationType;

public class TransferOperation {
    private final BankOperationType bankOperationType;
    private final Float amount;

    public TransferOperation(BankOperationType bankOperationType, Float amount) {
        this.bankOperationType = bankOperationType;
        this.amount = amount;
    }

    public BankOperationType getBankOperationType() {
        return bankOperationType;
    }

    public Float getAmount() {
        return amount;
    }
}
