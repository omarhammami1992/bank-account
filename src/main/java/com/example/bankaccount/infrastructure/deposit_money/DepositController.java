package com.example.bankaccount.infrastructure.deposit_money;

import com.example.bankaccount.common.domain.AccountId;
import com.example.bankaccount.domain.deposit_money.NotFoundBankAccount;
import com.example.bankaccount.use_case.DepositMoney;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DepositController {

    private final DepositMoney depositMoney;

    public DepositController(DepositMoney depositMoney) {
        this.depositMoney = depositMoney;
    }

    public ResponseEntity deposit(Integer accountIdValue, Float amount) {
        try {
            depositMoney.run(new AccountId(accountIdValue), amount);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NotFoundBankAccount e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
