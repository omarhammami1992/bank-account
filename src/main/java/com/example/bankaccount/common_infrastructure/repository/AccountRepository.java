package com.example.bankaccount.common_infrastructure.repository;

import com.example.bankaccount.common_infrastructure.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
}
