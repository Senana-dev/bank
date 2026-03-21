package com.dev.bank.service;

import com.dev.bank.dto.AccountResponse;
import com.dev.bank.dto.CreateAccountRequest;
import com.dev.bank.dto.TransactionHistoryResponse;
import com.dev.bank.dto.TransferRequest;
import com.dev.bank.entity.Account;
import com.dev.bank.entity.TransactionHistory;
import com.dev.bank.entity.TransactionType;
import com.dev.bank.repository.AccountRepository;
import com.dev.bank.repository.TransactionHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    public AccountService(AccountRepository accountRepository,
                          TransactionHistoryRepository transactionHistoryRepository) {
        this.accountRepository = accountRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    public AccountResponse createAccount(CreateAccountRequest request) {
        if (accountRepository.existsByAccountNumber(request.getAccountNumber())) {
            throw new IllegalArgumentException("Account number already exists.");
        }

        Account account = new Account(
                request.getAccountNumber(),
                request.getOwnerName(),
                request.getBalance()
        );

        Account saved = accountRepository.save(account);
        return toResponse(saved);
    }

    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AccountResponse getAccountByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found."));
        return toResponse(account);
    }

    public AccountResponse deposit(String accountNumber, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0.");
        }

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found."));

        account.setBalance(account.getBalance() + amount);
        Account saved = accountRepository.save(account);

        saveTransaction(TransactionType.DEPOSIT, amount, null, accountNumber);

        return toResponse(saved);
    }

    public AccountResponse withdraw(String accountNumber, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than 0.");
        }

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found."));

        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        account.setBalance(account.getBalance() - amount);
        Account saved = accountRepository.save(account);

        saveTransaction(TransactionType.WITHDRAW, amount, accountNumber, null);

        return toResponse(saved);
    }

    public Map<String, Object> transfer(TransferRequest request) {
        if (request.getFromAccountNumber().equals(request.getToAccountNumber())) {
            throw new IllegalArgumentException("Source and destination accounts must be different.");
        }

        Account fromAccount = accountRepository.findByAccountNumber(request.getFromAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Source account not found."));

        Account toAccount = accountRepository.findByAccountNumber(request.getToAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found."));

        if (fromAccount.getBalance() < request.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        fromAccount.setBalance(fromAccount.getBalance() - request.getAmount());
        toAccount.setBalance(toAccount.getBalance() + request.getAmount());

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        saveTransaction(
                TransactionType.TRANSFER,
                request.getAmount(),
                request.getFromAccountNumber(),
                request.getToAccountNumber()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Transfer completed successfully.");
        response.put("fromAccount", toResponse(fromAccount));
        response.put("toAccount", toResponse(toAccount));

        return response;
    }

    public List<TransactionHistoryResponse> getAllTransactions() {
        return transactionHistoryRepository.findAll()
                .stream()
                .map(this::toTransactionResponse)
                .toList();
    }

    public List<TransactionHistoryResponse> getTransactionsByAccount(String accountNumber) {
        return transactionHistoryRepository
                .findByFromAccountNumberOrToAccountNumberOrderByCreatedAtDesc(accountNumber, accountNumber)
                .stream()
                .map(this::toTransactionResponse)
                .toList();
    }

    private void saveTransaction(TransactionType type, double amount,
                                 String fromAccountNumber, String toAccountNumber) {
        TransactionHistory history = new TransactionHistory(
                type,
                amount,
                fromAccountNumber,
                toAccountNumber,
                LocalDateTime.now()
        );

        transactionHistoryRepository.save(history);
    }

    private AccountResponse toResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getOwnerName(),
                account.getBalance()
        );
    }

    private TransactionHistoryResponse toTransactionResponse(TransactionHistory history) {
        return new TransactionHistoryResponse(
                history.getId(),
                history.getType(),
                history.getAmount(),
                history.getFromAccountNumber(),
                history.getToAccountNumber(),
                history.getCreatedAt()
        );
    }
}
