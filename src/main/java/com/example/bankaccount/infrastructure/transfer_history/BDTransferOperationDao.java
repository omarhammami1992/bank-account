package com.example.bankaccount.infrastructure.transfer_history;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.common.BankServiceType;
import com.example.bankaccount.domain.transfer_history.TransferOperation;
import com.example.bankaccount.domain.transfer_history.TransferOperationDao;
import com.example.bankaccount.infrastructure.common.entity.Operation;
import com.example.bankaccount.infrastructure.common.repository.OperationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BDTransferOperationDao implements TransferOperationDao {

    private final OperationRepository operationRepository;

    public BDTransferOperationDao(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public List<TransferOperation> findByAccountId(AccountId accountId) {
        final List<Operation> operations = operationRepository.findByAccountIdAndBankServiceType(accountId.getValue(), BankServiceType.TRANSFER);
        return operations.stream()
                .map(operation -> new TransferOperation(operation.getBankOperationType(), operation.getAmount()))
                .collect(Collectors.toList());
    }
}
