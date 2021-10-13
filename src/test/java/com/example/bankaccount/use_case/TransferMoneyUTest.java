package com.example.bankaccount.use_case;

import com.example.bankaccount.common.domain.AccountId;
import com.example.bankaccount.common.domain.BankOperationType;
import com.example.bankaccount.common.domain.Iban;
import com.example.bankaccount.domain.transfer_money.BankAccount;
import com.example.bankaccount.domain.transfer_money.BankAccountDao;
import com.example.bankaccount.domain.transfer_money.BankOperation;
import com.example.bankaccount.common.domain.WithdrawException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferMoneyUTest {

    private TransferMoney transferMoney;

    @Mock
    private BankAccountDao bankAccountDao;

    @BeforeEach
    void setUp() {
        transferMoney = new TransferMoney(bankAccountDao);
    }

    @Nested
    class Run {
        @Test
        void should_update_payer_account() throws WithdrawException {
            // given
            AccountId payerAccountId = new AccountId(123);
            Iban payeeIban = new Iban("FR00000000000000000000");
            Float amount = 100f;

            final BankOperation payerOldBankOperation = new BankOperation(1, BankOperationType.DEPOSIT, 1000f);
            final List<BankOperation> oldBankOperation = new ArrayList<>(singletonList(payerOldBankOperation));

            final BankAccount payerBankAccount = new BankAccount(payerAccountId, oldBankOperation);
            when(bankAccountDao.findById(payerAccountId)).thenReturn(Optional.of(payerBankAccount));

            final List<BankOperation> bankOperationsToSave = asList(payerOldBankOperation, new BankOperation(BankOperationType.WITHDRAW, amount));
            final BankAccount expectedPayerBankAccountToSave = new BankAccount(payerAccountId, bankOperationsToSave);

            // when
            transferMoney.run(payerAccountId, payeeIban, amount);

            // then
            ArgumentCaptor<BankAccount> accountToSaveArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
            verify(bankAccountDao).save(accountToSaveArgumentCaptor.capture());
            assertThat(accountToSaveArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(expectedPayerBankAccountToSave);
        }
    }
}