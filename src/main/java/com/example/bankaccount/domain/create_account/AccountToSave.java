package com.example.bankaccount.domain.create_account;

import com.example.bankaccount.common.domain.Iban;

import static org.apache.logging.log4j.util.Strings.isBlank;

public class AccountToSave {

    private final String firstName;
    private final String lastName;
    private final Iban iban;

    public AccountToSave(UserInfo userInfo, Iban iban) throws InvalidUserInfoException {
        this.iban = iban;
        if (isBlank(userInfo.getFirstName()) || isBlank(userInfo.getLastName())) {
            throw new InvalidUserInfoException();
        }
        firstName = userInfo.getFirstName();
        lastName = userInfo.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Iban getIban() {
        return iban;
    }
}
