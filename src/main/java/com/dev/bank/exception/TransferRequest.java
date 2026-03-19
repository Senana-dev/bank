package com.dev.bank.exception;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public class TransferRequest {

    @NotBlank(message = "From account number is required.")
    private String fromAccountNumber;

    @NotBlank(message = "To account number is required.")
    private String toAccountNumber;

    @DecimalMin(value = "0.01", message = "Transfer amount must be greater than 0.")
    private double amount;

    public TransferRequest() {
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
