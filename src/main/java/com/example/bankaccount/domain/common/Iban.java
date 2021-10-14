package com.example.bankaccount.domain.common;

import java.util.Objects;

public class Iban {
    private final String value;

    public Iban(String ibanValue) {
        if (ibanValue == null) throw new MissingIbanException();
        this.value = ibanValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Iban iban = (Iban) o;

        return Objects.equals(value, iban.value);
    }

    @Override
    public String toString() {
        return value;
    }
}
