package com.example.bankaccount.use_case;

import com.example.bankaccount.common.domain.AccountId;
import com.example.bankaccount.common.domain.BankOperationType;
import com.example.bankaccount.common.domain.NotFoundBankAccount;
import com.example.bankaccount.common.domain.WithdrawException;
import com.example.bankaccount.domain.withdraw_money.BankAccount;
import com.example.bankaccount.domain.withdraw_money.BankAccountDao;
import com.example.bankaccount.domain.withdraw_money.BankOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class WithdrawMoneyUTest {

    private  WithdrawMoney withdrawMoney;

    @Mock
    private BankAccountDao bankAccountDao;

    @BeforeEach
    void setUp() {
        withdrawMoney = new WithdrawMoney(bankAccountDao);
    }

    @Nested
    class Run {

        @Test
        void should_save_withdraw_bank_operation_when_existing_budget() throws WithdrawException, NotFoundBankAccount {
            // given
            AccountId accountId = new AccountId(123);
            Float amount = 10f;

            final BankOperation bankOperation1 = new BankOperation(1, BankOperationType.DEPOSIT, 10f);
            final BankOperation bankOperation2 = new BankOperation(2, BankOperationType.DEPOSIT, 20f);
            final List<BankOperation> oldBankOperation = new ArrayList<>(asList(bankOperation1, bankOperation2));

            final BankAccount bankAccount = new BankAccount(accountId, oldBankOperation);
            when(bankAccountDao.find(accountId)).thenReturn(Optional.of(bankAccount));

            final List<BankOperation> bankOperationsToSave = asList(bankOperation1, bankOperation2, new BankOperation(BankOperationType.WITHDRAW, amount));
            final BankAccount expectedBankAccountToSave = new BankAccount(accountId, bankOperationsToSave);

            // when
            withdrawMoney.run(accountId, amount);

            // then
            ArgumentCaptor<BankAccount> bankAccountArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
            verify(bankAccountDao).save(bankAccountArgumentCaptor.capture());
            assertThat(bankAccountArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(expectedBankAccountToSave);
        }

        @Test
        void should_throw_WithdrawException_when_no_budget() {
            // given
            AccountId accountId = new AccountId(123);
            Float amount = 100f;

            final BankOperation bankOperation1 = new BankOperation(1, BankOperationType.DEPOSIT, 10f);
            final BankOperation bankOperation2 = new BankOperation(2, BankOperationType.DEPOSIT, 10f);
            final List<BankOperation> oldBankOperation = new ArrayList<>(asList(bankOperation1, bankOperation2));

            final BankAccount bankAccount = new BankAccount(accountId, oldBankOperation);
            when(bankAccountDao.find(accountId)).thenReturn(Optional.of(bankAccount));

            // then
            final Throwable throwable = catchThrowable(() -> withdrawMoney.run(accountId, amount));

            // then
            assertThat(throwable).isInstanceOf(WithdrawException.class);
        }

        @Test
        void should_throw_NotFoundBankAccount_when_no_found_bankAccount() {
            // given
            AccountId accountId = new AccountId(123);
            Float amount = 100f;

            when(bankAccountDao.find(accountId)).thenReturn(Optional.empty());

            // then
            final Throwable throwable = catchThrowable(() -> withdrawMoney.run(accountId, amount));

            // then
            assertThat(throwable).isInstanceOf(NotFoundBankAccount.class);
        }
    }
}