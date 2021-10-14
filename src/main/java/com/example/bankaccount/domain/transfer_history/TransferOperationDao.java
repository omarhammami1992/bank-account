package com.example.bankaccount.domain.transfer_history;

import com.example.bankaccount.domain.common.AccountId;

import java.util.List;

public interface TransferOperationDao {
    List<TransferOperation> findByAccountId(AccountId accountId);
}
