package com.example.bankaccount.infrastructure.create_account;

import com.example.bankaccount.domain.create_account.InvalidUserInfoException;
import com.example.bankaccount.domain.create_account.UserInfo;
import com.example.bankaccount.use_case.CreateAccount;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AccountController {

    private final CreateAccount createAccount;

    public AccountController(CreateAccount createAccount) {
        this.createAccount = createAccount;
    }

    public ResponseEntity createAccount(UserInfo userInfo) {
        try {
            createAccount.run(userInfo);
            return new ResponseEntity(HttpStatus.OK);
        } catch (InvalidUserInfoException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
