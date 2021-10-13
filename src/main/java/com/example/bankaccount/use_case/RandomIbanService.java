package com.example.bankaccount.use_case;

import com.example.bankaccount.domain.IbanService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomIbanService implements IbanService {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
