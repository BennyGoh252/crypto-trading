package com.example.crypto_trading.modal;

import java.math.BigInteger;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;

@Entity
public class Wallets {
    @Id
    private BigInteger id;

    private BigInteger userId;

    @Column(name = "currency", length = 10)  // VARCHAR(10) for the 'currency' field
    private String currency;
    
    @Column(name = "balance", precision = 20, scale = 8)  // DECIMAL(20,8)
    private BigDecimal balance;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
   
}
