package com.example.bankaccount.use_case;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.transfer_history.TransferOperation;
import com.example.bankaccount.domain.transfer_history.TransferOperationDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTransferHistoryUTest {

    private GetTransferHistory getTransferHistory;

    @Mock
    private TransferOperationDao transferOperationDao;

    @BeforeEach
    void setUp() {
        getTransferHistory = new GetTransferHistory(transferOperationDao);
    }

    @Nested
    class Run {

        private final AccountId accountId = new AccountId(111);

        @Test
        void should_return_transfer_operations() {
            // given
            final List<TransferOperation> expectedTransferOperations = Collections.singletonList(mock(TransferOperation.class));
            when(transferOperationDao.findByAccountId(accountId)).thenReturn(expectedTransferOperations);

            // when
            final List<TransferOperation> transferOperations = getTransferHistory.run(accountId);

            // then
            assertThat(transferOperations).isEqualTo(expectedTransferOperations);
        }

    }
}