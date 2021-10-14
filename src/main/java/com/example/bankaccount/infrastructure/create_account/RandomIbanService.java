package com.example.bankaccount.infrastructure.create_account;

import com.example.bankaccount.domain.common.Iban;
import com.example.bankaccount.domain.create_account.IbanService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomIbanService implements IbanService {

    @Override
    public Iban generate() {
        return new Iban("FR" + UUID.randomUUID());
    }
}
