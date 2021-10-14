package com.example.bankaccount.domain.deposit_money;

import com.example.bankaccount.domain.common.AccountId;

import java.util.Optional;

public interface BankAccountDao {
    Optional<BankAccount> find(AccountId accountId);
    void save(BankAccount bankAccount);
}
