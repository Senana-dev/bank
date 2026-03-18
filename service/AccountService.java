package com.dev.bank.service;

import com.dev.bank.entity.Account;
import com.dev.bank.repository.AccountRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        if (accountRepository.existsByAccountNumber(account.getAccountNumber())) {
            throw new IllegalArgumentException("Account number already exists.");
        }

        if (account.getBalance() < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }

        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found."));
    }

    public Account deposit(String accountNumber, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0.");
        }

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found."));

        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }

    public Account withdraw(String accountNumber, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than 0.");
        }

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found."));

        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }
}
