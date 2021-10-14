package com.example.bankaccount.infrastructure.common.entity;

import com.example.bankaccount.domain.common.BankOperationType;
import com.example.bankaccount.domain.common.BankServiceType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BankOperationType bankOperationType;

    private BankServiceType bankServiceType;

    private Float amount;

    public Operation(Integer id, BankOperationType bankOperationType, BankServiceType bankServiceType, Float amount) {
        this.id = id;
        this.bankOperationType = bankOperationType;
        this.bankServiceType = bankServiceType;
        this.amount = amount;
    }

    public Operation() {

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
