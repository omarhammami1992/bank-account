package com.example.bankaccount.domain;

import static org.apache.logging.log4j.util.Strings.isBlank;

public class AccountToSave {

    private final String firstName;
    private final String lastName;
    private final String iban;

    private AccountToSave(UserInfo userInfo, String iban) throws InvalidUserInfoException {
        this.iban = iban;
        if (isBlank(userInfo.getFirstName()) || isBlank(userInfo.getLastName())) {
            throw new InvalidUserInfoException();
        }
        firstName = userInfo.getFirstName();
        lastName = userInfo.getLastName();
    }

    public static AccountToSave create(UserInfo userInfo, String iban) throws InvalidUserInfoException {
        return new AccountToSave(userInfo, iban);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIban() {
        return iban;
    }
}
