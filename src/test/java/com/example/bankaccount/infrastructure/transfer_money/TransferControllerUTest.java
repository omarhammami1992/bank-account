package com.example.bankaccount.infrastructure.transfer_money;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.common.Iban;
import com.example.bankaccount.domain.common.NotFoundBankAccount;
import com.example.bankaccount.domain.common.WithdrawException;
import com.example.bankaccount.use_case.TransferMoney;
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
class TransferControllerUTest {
    private TransferController transferController;

    @Mock
    private TransferMoney transferMoney;

    @BeforeEach
    void setUp() {
        transferController = new TransferController(transferMoney);
    }

    @Nested
    class TransferShould {
        private final Integer payerAccountIdValue = 123;
        private final String payeeIbanValue = "FR000000000000";
        private final Float amount = 26f;

        @Test
        void return_200_when_transfer_is_done() {
            // when
            final ResponseEntity responseEntity = transferController.transfer(payerAccountIdValue, payeeIbanValue, amount);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        void return_400_when_payer_or_payee_account_not_found() throws NotFoundBankAccount, WithdrawException {
            // given
            doThrow(NotFoundBankAccount.class).when(transferMoney).run(new AccountId(payerAccountIdValue), new Iban(payeeIbanValue), amount);

            // when
            final ResponseEntity responseEntity = transferController.transfer(payerAccountIdValue, payeeIbanValue, amount);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void return_500_when_no_budget_in_payer_account() throws NotFoundBankAccount, WithdrawException {
            // given
            doThrow(WithdrawException.class).when(transferMoney).run(new AccountId(payerAccountIdValue), new Iban(payeeIbanValue), amount);

            // when
            final ResponseEntity responseEntity = transferController.transfer(payerAccountIdValue, payeeIbanValue, amount);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}