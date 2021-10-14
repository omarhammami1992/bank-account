package com.example.bankaccount.infrastructure.transfer_history;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.transfer_history.TransferOperation;
import com.example.bankaccount.use_case.GetTransferHistory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class HistoryController {

    private final GetTransferHistory getTransferHistory;

    public HistoryController(GetTransferHistory getTransferHistory) {
        this.getTransferHistory = getTransferHistory;
    }

    public ResponseEntity<TransferOperation> getTransferHistory(Integer accountIdValue) {
        final List<TransferOperation> transferOperations = getTransferHistory.run(new AccountId(accountIdValue));
        return new ResponseEntity(transferOperations, HttpStatus.OK);
    }
}
