package com.example.bankaccount.infrastructure.deposit_money;

import com.example.bankaccount.common.domain.AccountId;
import com.example.bankaccount.domain.deposit_money.NotFoundBankAccount;
import com.example.bankaccount.use_case.DepositMoney;
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
class DepositControllerUTest {

    private DepositController depositController;

    @Mock
    private DepositMoney depositMoney;

    @BeforeEach
    void setUp() {
        depositController = new DepositController(depositMoney);
    }

    @Nested
    class DepositShould {
        private final Integer accountIdValue = 123;
        private final Float amount = 26f;

        @Test
        void return_200_when_user_account_is_created() {
            // when
            final ResponseEntity responseEntity = depositController.deposit(accountIdValue, amount);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        void return_400_when_user_account_is_not_found() throws NotFoundBankAccount {
            // given
            doThrow(NotFoundBankAccount.class).when(depositMoney).run(new AccountId(accountIdValue), amount);

            // when
            final ResponseEntity responseEntity = depositController.deposit(accountIdValue, amount);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }
}