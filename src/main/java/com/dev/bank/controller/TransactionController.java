package com.dev.bank.controller;

import com.dev.bank.dto.TransactionHistoryResponse;
import com.dev.bank.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final AccountService accountService;

    public TransactionController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<TransactionHistoryResponse> getAllTransactions() {
        return accountService.getAllTransactions();
    }

    @GetMapping("/{accountNumber}")
    public List<TransactionHistoryResponse> getTransactionsByAccount(@PathVariable String accountNumber) {
        return accountService.getTransactionsByAccount(accountNumber);
    }
}
