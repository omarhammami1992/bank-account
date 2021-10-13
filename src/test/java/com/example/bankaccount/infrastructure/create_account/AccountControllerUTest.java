package com.example.bankaccount.infrastructure.create_account;

import com.example.bankaccount.domain.create_account.InvalidUserInfoException;
import com.example.bankaccount.domain.create_account.UserInfo;
import com.example.bankaccount.use_case.CreateAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class AccountControllerUTest {

    private AccountController accountController;

    @Mock
    private CreateAccount createAccount;

    @BeforeEach
    void setUp() {
        accountController = new AccountController(createAccount);
    }

    @Nested
    class CreateAccountShould {
        @Test
        void return_200_when_user_account_is_created() {
            // given
            UserInfo userInfo = new FrontUserInfo("Omar", "Hammami");

            // when
            final ResponseEntity responseEntity = accountController.createAccount(userInfo);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        void return_400_when_user_account_is_not_created() throws InvalidUserInfoException {
            // given
            UserInfo userInfo = new FrontUserInfo("Omar", null);
            doThrow(InvalidUserInfoException.class).when(createAccount).run(userInfo);

            // when
            final ResponseEntity responseEntity = accountController.createAccount(userInfo);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }
}