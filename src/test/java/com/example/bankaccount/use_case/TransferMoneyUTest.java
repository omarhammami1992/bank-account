package com.example.bankaccount.use_case;

import com.example.bankaccount.common.domain.AccountId;
import com.example.bankaccount.common.domain.Iban;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TransferMoneyUTest {

    private TransferMoney transferMoney;

    @BeforeEach
    void setUp() {
        transferMoney = new TransferMoney();
    }

    @Nested
    class Run {
        @Test
        void name() {
            // given
            AccountId payerAccountId = new AccountId(123);
            Iban payeeIban = new Iban("FR00000000000000000000");
            Float amount = 100f;

            // when
            transferMoney.run(payerAccountId, payeeIban, amount);
            // then
        }
    }
}