package com.example.bankaccount.use_case;

import com.example.bankaccount.domain.AccountDao;
import com.example.bankaccount.domain.AccountToSave;
import com.example.bankaccount.domain.InvalidUserInfoException;
import com.example.bankaccount.domain.UserInfo;
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
class CreateAccountUTest {

    private CreateAccount createAccount;

    @Mock
    private AccountDao accountDao;

    @BeforeEach
    void setUp() {
        createAccount = new CreateAccount(accountDao);
    }

    @Nested
    class Run {
        @Test
        void should_call_accountDao_save() throws InvalidUserInfoException {
            // given
            UserInfo userInfo = mock(UserInfo.class);
            when(userInfo.getFirstName()).thenReturn("Omar");
            when(userInfo.getLastName()).thenReturn("Hammami");

            AccountToSave accountToSave = AccountToSave.create(userInfo);

            // when
            createAccount.run(userInfo);

            // then
            ArgumentCaptor<AccountToSave> accountToSaveArgumentCaptor = ArgumentCaptor.forClass(AccountToSave.class);
            verify(accountDao).save(accountToSaveArgumentCaptor.capture());
            assertThat(accountToSaveArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(accountToSave);
        }
    }

}