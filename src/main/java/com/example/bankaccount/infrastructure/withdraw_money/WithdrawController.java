package com.example.bankaccount.infrastructure.withdraw_money;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.common.NotFoundBankAccount;
import com.example.bankaccount.domain.common.WithdrawException;
import com.example.bankaccount.use_case.WithdrawMoney;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WithdrawController {

    private final WithdrawMoney withdrawMoney;

    public WithdrawController(WithdrawMoney withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    public ResponseEntity withdraw(Integer accountIdValue, Float amount) {
        try {
            withdrawMoney.run(new AccountId(accountIdValue), amount);
            return new ResponseEntity(HttpStatus.OK);
        } catch (WithdrawException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundBankAccount e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
