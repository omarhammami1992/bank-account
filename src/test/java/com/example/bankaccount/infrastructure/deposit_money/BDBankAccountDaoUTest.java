package com.example.bankaccount.infrastructure.deposit_money;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.common.BankOperationType;
import com.example.bankaccount.domain.common.BankServiceType;
import com.example.bankaccount.infrastructure.common.entity.Account;
import com.example.bankaccount.infrastructure.common.entity.Operation;
import com.example.bankaccount.infrastructure.common.repository.AccountRepository;
import com.example.bankaccount.domain.deposit_money.BankAccount;
import com.example.bankaccount.domain.deposit_money.BankAccountDao;
import com.example.bankaccount.domain.deposit_money.BankOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BDBankAccountDaoUTest {

    private BankAccountDao bankAccountDao;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        bankAccountDao = new BDBankAccountDao(accountRepository);
    }

    @Nested
    class FindShould {
        @Test
        void should_return_optinal_of_bankAccount_when_found() {
            // given
            final int accountIdValue = 123;
            AccountId accountId = new AccountId(accountIdValue);

            final Account account = mock(Account.class);
            when(account.getId()).thenReturn(accountIdValue);

            final int operationId = 1;
            final BankOperationType bankOperationType = BankOperationType.CREDIT;
            final BankServiceType bankServiceType = BankServiceType.DEPOSIT;
            final Float amount = 30f;
            final Operation operation = new Operation(operationId, bankOperationType, bankServiceType, amount, accountIdValue);

            when(account.getOperations()).thenReturn(singletonList(operation));
            when(accountRepository.findById(accountIdValue)).thenReturn(Optional.of(account));

            BankAccount expectedBankAccount = new BankAccount(accountId, singletonList(new BankOperation(operationId, bankOperationType, bankServiceType, amount)));

            // when
            final Optional<BankAccount> optionalBankAccount = bankAccountDao.find(accountId);

            // then
            assertThat(optionalBankAccount.get()).usingRecursiveComparison().isEqualTo(expectedBankAccount);

        }

        @Test
        void should_return_empty_when_found() {
            // given
            final int accountIdValue = 123;
            AccountId accountId = new AccountId(accountIdValue);

            when(accountRepository.findById(accountIdValue)).thenReturn(Optional.empty());

            // when
            final Optional<BankAccount> optionalBankAccount = bankAccountDao.find(accountId);

            // then
            assertThat(optionalBankAccount.isPresent()).isFalse();

        }
    }

    @Nested
    class Save {

        @Test
        void should_call_accountRepository() {
            // given
            final BankOperationType bankOperationType = BankOperationType.CREDIT;
            final BankServiceType bankServiceType = BankServiceType.DEPOSIT;
            final float amount = 10f;
            final BankOperation bankOperation = new BankOperation(bankOperationType, bankServiceType, amount);
            final int accountIdValue = 123;
            final BankAccount bankAccount = new BankAccount(new AccountId(accountIdValue), singletonList(bankOperation));

            final Operation operation = new Operation(null, bankOperationType, bankServiceType, amount, accountIdValue);

            Account accountToSave = new Account(accountIdValue, singletonList(operation));

            // when
            bankAccountDao.save(bankAccount);

            // then
            ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);
            verify(accountRepository).save(accountArgumentCaptor.capture());
            assertThat(accountArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(accountToSave);
        }
    }
}