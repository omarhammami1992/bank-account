package com.example.bankaccount.domain.common;

import java.util.Objects;

public class AccountId {
    private final Integer value;

    public AccountId(Integer accountIdValue) {
        if (accountIdValue == null) throw new MissingAccountIdException();
        this.value = accountIdValue;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountId accountId = (AccountId) o;

        return Objects.equals(value, accountId.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
