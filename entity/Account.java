package com.dev.bank.entity;

import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String accountNumber;

    private String ownerName;

    private double balance;

    public Account() {}

    public Account(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public Long getId() { return id; }

    public String getAccountNumber() { return accountNumber; }

    public String getOwnerName() { return ownerName; }

    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }
}
