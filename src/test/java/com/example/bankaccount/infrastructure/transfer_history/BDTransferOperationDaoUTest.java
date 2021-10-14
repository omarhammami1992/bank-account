package com.example.bankaccount.infrastructure.transfer_history;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.common.BankOperationType;
import com.example.bankaccount.domain.common.BankServiceType;
import com.example.bankaccount.domain.transfer_history.TransferOperation;
import com.example.bankaccount.domain.transfer_history.TransferOperationDao;
import com.example.bankaccount.infrastructure.common.entity.Operation;
import com.example.bankaccount.infrastructure.common.repository.OperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BDTransferOperationDaoUTest {

    private TransferOperationDao transferOperationDao;

    @Mock
    private OperationRepository operationRepository;

    @BeforeEach
    void setUp() {
        transferOperationDao = new BDTransferOperationDao(operationRepository);
    }

    @Nested
    class FindByAccountId {
        @Test
        void should_return_transferOperation_by_user_account() {
            // given
            final AccountId accountId = new AccountId(123);
            final Operation operation = new Operation(1, BankOperationType.CREDIT, BankServiceType.TRANSFER, 100F, accountId.getValue());
            when(operationRepository.findByAccountIdAndBankServiceType(accountId.getValue(), BankServiceType.TRANSFER)).thenReturn(Collections.singletonList(operation));

            TransferOperation transferOperation = new TransferOperation(BankOperationType.CREDIT, 100F);

            // when
            final List<TransferOperation> transferOperations = transferOperationDao.findByAccountId(accountId);

            // then
            assertThat(transferOperations).usingRecursiveFieldByFieldElementComparator().containsExactly(transferOperation);
        }
    }
}