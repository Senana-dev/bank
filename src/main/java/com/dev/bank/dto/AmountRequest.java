package com.dev.bank.dto;

import jakarta.validation.constraints.DecimalMin;

public class AmountRequest {

    @DecimalMin(value = "0.01", message = "Amount must be greater than 0.")
    private double amount;

    public AmountRequest() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
