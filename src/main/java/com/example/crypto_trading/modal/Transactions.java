package com.example.crypto_trading.modal;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private BigInteger userId;

    @Column(name = "pair", length = 20)  // VARCHAR(20) for the 'pair' field
    private String pair;

    @Column(name = "type", length = 10)  // VARCHAR(10) for the 'type' field
    private String type;
    
    @Column(name = "price", precision = 20, scale = 8)  // DECIMAL(20,8)
    private BigDecimal price;

    @Column(name = "amount", precision = 20, scale = 8)  // DECIMAL(20,8)
    private BigDecimal amount;

    private LocalDateTime timestamp;

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

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
   
}
