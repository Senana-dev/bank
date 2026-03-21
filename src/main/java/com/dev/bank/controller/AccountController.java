package com.dev.bank.controller;

import com.dev.bank.dto.AccountResponse;
import com.dev.bank.dto.AmountRequest;
import com.dev.bank.dto.CreateAccountRequest;
import com.dev.bank.dto.TransferRequest;
import com.dev.bank.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountResponse createAccount(@Valid @RequestBody CreateAccountRequest request) {
        return accountService.createAccount(request);
    }

    @GetMapping
    public List<AccountResponse> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{accountNumber}")
    public AccountResponse getAccount(@PathVariable String accountNumber) {
        return accountService.getAccountByAccountNumber(accountNumber);
    }

    @PostMapping("/{accountNumber}/deposit")
    public AccountResponse deposit(@PathVariable String accountNumber,
                                   @Valid @RequestBody AmountRequest request) {
        return accountService.deposit(accountNumber, request.getAmount());
    }

    @PostMapping("/{accountNumber}/withdraw")
    public AccountResponse withdraw(@PathVariable String accountNumber,
                                    @Valid @RequestBody AmountRequest request) {
        return accountService.withdraw(accountNumber, request.getAmount());
    }

    @PostMapping("/transfer")
    public Map<String, Object> transfer(@Valid @RequestBody TransferRequest request) {
        return accountService.transfer(request);
    }
}
