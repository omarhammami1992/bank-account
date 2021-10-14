package com.example.bankaccount.use_case;

import com.example.bankaccount.domain.common.*;
import com.example.bankaccount.domain.transfer_money.BankAccount;
import com.example.bankaccount.domain.transfer_money.BankAccountDao;
import com.example.bankaccount.domain.transfer_money.BankOperation;
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
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

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

        private final AccountId payerAccountId = new AccountId(111);
        private final AccountId payeeAccountId = new AccountId(222);
        private final Iban payeeIban = new Iban("FR00000000000000000000");
        private final Float amount = 100f;

        private BankOperation payerOldBankOperation;

        @Nested
        class WhenTransferSucceeded {

            @BeforeEach
            void setUp() {
                payerOldBankOperation = new BankOperation(1, BankOperationType.CREDIT, 1000f);
                final List<BankOperation> oldBankOperation = new ArrayList<>(singletonList(payerOldBankOperation));

                BankAccount payerBankAccount = new BankAccount(payerAccountId, oldBankOperation);
                when(bankAccountDao.findById(payerAccountId)).thenReturn(Optional.of(payerBankAccount));

                final BankAccount payeeBankAccount = new BankAccount(payeeAccountId, new ArrayList<>());
                when(bankAccountDao.findByIban(payeeIban)).thenReturn(Optional.of(payeeBankAccount));
            }

            @Test
            void should_update_payer_account() throws WithdrawException, NotFoundBankAccount {
                // given
                final List<BankOperation> payerBankOperationsToSave = asList(payerOldBankOperation, new BankOperation(BankOperationType.DEBIT, amount));
                final BankAccount expectedPayerBankAccountToSave = new BankAccount(payerAccountId, payerBankOperationsToSave);

                // when
                transferMoney.run(payerAccountId, payeeIban, amount);

                // then
                ArgumentCaptor<BankAccount> bankAccountArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
                verify(bankAccountDao, times(2)).save(bankAccountArgumentCaptor.capture());
                assertThat(bankAccountArgumentCaptor.getAllValues().get(0)).usingRecursiveComparison().isEqualTo(expectedPayerBankAccountToSave);
            }

            @Test
            void should_update_payee_account() throws WithdrawException, NotFoundBankAccount {
                // given
                final BankAccount expectedPayeeBankAccountToSave = new BankAccount(payeeAccountId, singletonList(new BankOperation(BankOperationType.CREDIT, amount)));

                // when
                transferMoney.run(payerAccountId, payeeIban, amount);

                // then
                ArgumentCaptor<BankAccount> bankAccountArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
                verify(bankAccountDao, times(2)).save(bankAccountArgumentCaptor.capture());
                assertThat(bankAccountArgumentCaptor.getAllValues().get(1)).usingRecursiveComparison().isEqualTo(expectedPayeeBankAccountToSave);
            }
        }

        @Test
        void should_NotFoundBankAccount_when_not_found_payer_account() {
            // given
            when(bankAccountDao.findById(payerAccountId)).thenReturn(Optional.empty());

            // when
            final Throwable throwable = catchThrowable(() -> transferMoney.run(payerAccountId, payeeIban, amount));

            // then
            assertThat(throwable).isInstanceOf(NotFoundBankAccount.class);
        }

        @Test
        void should_WithdrawException_when_not_payer_has_no_budget() {
            // given
            BankAccount payerBankAccount = new BankAccount(payerAccountId, new ArrayList<>());
            when(bankAccountDao.findById(payerAccountId)).thenReturn(Optional.of(payerBankAccount));

            BankAccount payeeBankAccount = new BankAccount(payeeAccountId, new ArrayList<>());
            when(bankAccountDao.findByIban(payeeIban)).thenReturn(Optional.of(payeeBankAccount));

            // when
            final Throwable throwable = catchThrowable(() -> transferMoney.run(payerAccountId, payeeIban, amount));

            // then
            assertThat(throwable).isInstanceOf(WithdrawException.class);
        }

    }
}