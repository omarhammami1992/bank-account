package com.example.bankaccount.infrastructure.create_account;

import com.example.bankaccount.common.domain.Iban;
import com.example.bankaccount.common.infrastructure.entity.Account;
import com.example.bankaccount.common.infrastructure.repository.AccountRepository;
import com.example.bankaccount.domain.create_account.AccountDao;
import com.example.bankaccount.domain.create_account.AccountToSave;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BDAccountDaoUTest {

    private AccountDao accountDao;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountDao = new BDAccountDao(accountRepository);
    }

    @Nested
    class Save {
        @Test
        void should_call_accountRepository_save() {
            // given
            AccountToSave accountToSave = mock(AccountToSave.class);
            final String firstName = "Omar";
            when(accountToSave.getFirstName()).thenReturn(firstName);
            final String lastName = "Hammami";
            when(accountToSave.getLastName()).thenReturn(lastName);
            final String ibanValue = "FR76000000000000000000000000";
            when(accountToSave.getIban()).thenReturn(new Iban(ibanValue));

            Account account = new Account(firstName, lastName, ibanValue);

            // when
            accountDao.save(accountToSave);

            // then
            ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);
            verify(accountRepository).save(accountArgumentCaptor.capture());
            assertThat(accountArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(account);

        }
    }
}