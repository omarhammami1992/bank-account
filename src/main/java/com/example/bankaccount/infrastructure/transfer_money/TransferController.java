package com.example.bankaccount.infrastructure.transfer_money;

import com.example.bankaccount.domain.common.AccountId;
import com.example.bankaccount.domain.common.Iban;
import com.example.bankaccount.domain.common.NotFoundBankAccount;
import com.example.bankaccount.domain.common.WithdrawException;
import com.example.bankaccount.use_case.TransferMoney;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TransferController {

    private final TransferMoney transferMoney;

    public TransferController(TransferMoney transferMoney) {
        this.transferMoney = transferMoney;
    }

    public ResponseEntity transfer(Integer payerAccountIdValue, String payeeIbanValue, Float amount) {
        try {
            transferMoney.run(new AccountId(payerAccountIdValue), new Iban(payeeIbanValue), amount);
            return new ResponseEntity(HttpStatus.OK);
        } catch (WithdrawException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundBankAccount e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
