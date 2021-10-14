package com.example.bankaccount.infrastructure.transfer_history;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.common.BankOperationType;
import com.example.bankaccount.domain.transfer_history.TransferOperation;
import com.example.bankaccount.use_case.GetTransferHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistoryControllerUTest {


    private HistoryController historyController;

    @Mock
    private GetTransferHistory getTransferHistory;

    @BeforeEach
    void setUp() {
        historyController = new HistoryController(getTransferHistory);
    }

    @Test
    void getTransferHistory_should_return_200_with_transferOperations() {
        // given
        AccountId accountId = new AccountId(123);
        final List<TransferOperation> transferOperations = Collections.singletonList(new TransferOperation(BankOperationType.CREDIT, 100f));
        when(getTransferHistory.run(accountId)).thenReturn(transferOperations);

        // when
        final ResponseEntity<TransferOperation> responseEntity = historyController.getTransferHistory(accountId.getValue());

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(transferOperations);
    }
}