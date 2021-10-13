package com.example.bankaccount.domain;

import static org.apache.logging.log4j.util.Strings.isBlank;

public class AccountToSave {

    private final String firstName;
    private final String lastName;

    private AccountToSave(UserInfo userInfo) throws InvalidUserInfoException {
        if (isBlank(userInfo.getFirstName()) || isBlank(userInfo.getLastName())) {
            throw new InvalidUserInfoException();
        }
        firstName = userInfo.getFirstName();
        lastName = userInfo.getLastName();
    }

    public static AccountToSave create(UserInfo userInfo) throws InvalidUserInfoException {
        return new AccountToSave(userInfo);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
