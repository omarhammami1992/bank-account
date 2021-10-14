package com.example.bankaccount.infrastructure.withdraw_money;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.common.NotFoundBankAccount;
import com.example.bankaccount.domain.common.WithdrawException;
import com.example.bankaccount.use_case.WithdrawMoney;
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
class WithdrawControllerUTest {
    private WithdrawController withdrawController;

    @Mock
    private WithdrawMoney withdrawMoney;

    @BeforeEach
    void setUp() {
        withdrawController = new WithdrawController(withdrawMoney);
    }

    @Nested
    class WithdrawShould {
        private final Integer accountIdValue = 123;
        private final Float amount = 26f;

        @Test
        void return_200_when_withdraw_operation_is_done() {
            // when
            final ResponseEntity responseEntity = withdrawController.withdraw(accountIdValue, amount);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        void return_400_when_user_account_is_not_found() throws NotFoundBankAccount, WithdrawException {
            // given
            doThrow(NotFoundBankAccount.class).when(withdrawMoney).run(new AccountId(accountIdValue), amount);

            // when
            final ResponseEntity responseEntity = withdrawController.withdraw(accountIdValue, amount);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void return_500_when_no_budget() throws NotFoundBankAccount, WithdrawException {
            // given
            doThrow(WithdrawException.class).when(withdrawMoney).run(new AccountId(accountIdValue), amount);

            // when
            final ResponseEntity responseEntity = withdrawController.withdraw(accountIdValue, amount);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}