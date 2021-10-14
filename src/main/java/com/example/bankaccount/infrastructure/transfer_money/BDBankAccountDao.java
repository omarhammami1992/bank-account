package com.example.bankaccount.infrastructure.transfer_money;

import com.example.bankaccount.common.domain.AccountId;
import com.example.bankaccount.common.domain.Iban;
import com.example.bankaccount.common.infrastructure.entity.Account;
import com.example.bankaccount.common.infrastructure.entity.Operation;
import com.example.bankaccount.common.infrastructure.repository.AccountRepository;
import com.example.bankaccount.domain.transfer_money.BankAccount;
import com.example.bankaccount.domain.transfer_money.BankAccountDao;
import com.example.bankaccount.domain.transfer_money.BankOperation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("transfer_money.BDBankAccountDao")
public class BDBankAccountDao implements BankAccountDao {

    private final AccountRepository accountRepository;

    public BDBankAccountDao(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<BankAccount> findById(AccountId accountId) {
        final Optional<Account> optionalAccount = accountRepository.findById(accountId.getValue());
        if (optionalAccount.isPresent()) {
            final Account account = optionalAccount.get();
            final List<BankOperation> bankOperations = buildBankOperations(account.getOperations());
            return Optional.of(new BankAccount(new AccountId(account.getId()), bankOperations));
        }
        return Optional.empty();
    }

    @Override
    public Optional<BankAccount> findByIban(Iban iban) {
        final Optional<Account> optionalAccount = accountRepository.findByIban(iban.toString());
        if (optionalAccount.isPresent()) {
            final Account account = optionalAccount.get();
            final List<BankOperation> bankOperations = buildBankOperations(account.getOperations());
            return Optional.of(new BankAccount(new AccountId(account.getId()), bankOperations));
        }
        return Optional.empty();
    }

    @Override
    public void save(BankAccount bankAccount) {
        final List<Operation> operations = buildOperations(bankAccount.getBankOperations());
        accountRepository.save(new Account(bankAccount.getAccountId().getValue(), operations));
    }

    private List<BankOperation> buildBankOperations(List<Operation> operations) {
        return operations.stream()
                .map(operation -> new BankOperation(operation.getId(), operation.getBankOperationType(), operation.getAmount()))
                .collect(Collectors.toList());
    }

    private List<Operation> buildOperations(List<BankOperation> bankOperations) {
        return bankOperations.stream()
                .map(bankOperation -> new Operation(bankOperation.getId(), bankOperation.getBankOperationType(), bankOperation.getAmount()))
                .collect(Collectors.toList());
    }
}
