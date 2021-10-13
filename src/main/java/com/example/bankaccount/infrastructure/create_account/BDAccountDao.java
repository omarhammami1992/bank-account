package com.example.bankaccount.infrastructure.create_account;

import com.example.bankaccount.common.infrastructure.entity.Account;
import com.example.bankaccount.common.infrastructure.repository.AccountRepository;
import com.example.bankaccount.domain.create_account.AccountDao;
import com.example.bankaccount.domain.create_account.AccountToSave;
import org.springframework.stereotype.Repository;

@Repository
public class BDAccountDao implements AccountDao {

    private final AccountRepository accountRepository;

    public BDAccountDao(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void save(AccountToSave accountToSave) {
        Account account = new Account(accountToSave.getFirstName(), accountToSave.getLastName(), accountToSave.getIban());
        accountRepository.save(account);

    }
}
