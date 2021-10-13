package com.example.bankaccount.infrastructure.create_account;

import com.example.bankaccount.domain.create_account.UserInfo;

public class FrontUserInfo implements UserInfo {
    private final String firstName;
    private final String lastName;

    public FrontUserInfo(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}
