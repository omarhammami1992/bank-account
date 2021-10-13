package com.example.bankaccount.common.infrastructure.repository;

import com.example.bankaccount.common.infrastructure.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Override
    Optional<Account> findById(Integer id);

    Optional<Account> findByIban(String iban);
}
