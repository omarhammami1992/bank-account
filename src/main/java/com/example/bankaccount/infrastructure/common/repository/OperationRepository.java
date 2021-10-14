package com.example.bankaccount.infrastructure.common.repository;

import com.example.bankaccount.domain.common.BankServiceType;
import com.example.bankaccount.infrastructure.common.entity.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Integer> {

    List<Operation> findByAccountIdAndBankServiceType(Integer accountId, BankServiceType bankServiceType);
}
