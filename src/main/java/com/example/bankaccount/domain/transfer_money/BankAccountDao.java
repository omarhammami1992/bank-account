package com.example.bankaccount.domain.transfer_money;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.common.Iban;

import java.util.Optional;

public interface BankAccountDao {
    Optional<BankAccount> findById(AccountId accountId);
    Optional<BankAccount> findByIban(Iban accountId);
    void save(BankAccount bankAccount);
}
