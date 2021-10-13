package com.example.bankaccount.infrastructure.create_account;

import com.example.bankaccount.domain.create_account.IbanService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomIbanService implements IbanService {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
