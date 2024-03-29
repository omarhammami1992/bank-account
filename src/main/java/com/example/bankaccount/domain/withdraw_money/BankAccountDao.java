package com.example.bankaccount.domain.withdraw_money;

import com.example.bankaccount.domain.common.AccountId;

import java.util.Optional;

public interface BankAccountDao {
    Optional<BankAccount> find(AccountId accountId);
    void save(BankAccount bankAccount);
}
