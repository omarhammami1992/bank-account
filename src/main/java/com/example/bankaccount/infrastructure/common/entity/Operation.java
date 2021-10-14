package com.example.bankaccount.infrastructure.common.entity;

import com.example.bankaccount.domain.common.BankOperationType;

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

    private Float amount;

    public Operation(Integer id, BankOperationType bankOperationType, Float amount) {
        this.id = id;
        this.bankOperationType = bankOperationType;
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

    public Float getAmount() {
        return amount;
    }
}
