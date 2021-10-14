package com.example.bankaccount.use_case;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.transfer_history.TransferOperation;
import com.example.bankaccount.domain.transfer_history.TransferOperationDao;

import java.util.List;

public class GetTransferHistory {
    private final TransferOperationDao transferOperationDao;

    public GetTransferHistory(TransferOperationDao transferOperationDao) {
        this.transferOperationDao = transferOperationDao;
    }

    public List<TransferOperation> run(AccountId accountId) {
        return transferOperationDao.findByAccountId(accountId);
    }
}
