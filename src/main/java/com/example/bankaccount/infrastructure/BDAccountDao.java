package com.example.bankaccount.infrastructure;

import com.example.bankaccount.common_infrastructure.entity.Account;
import com.example.bankaccount.common_infrastructure.repository.AccountRepository;
import com.example.bankaccount.domain.AccountDao;
import com.example.bankaccount.domain.AccountToSave;
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
