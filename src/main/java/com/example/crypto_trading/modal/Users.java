package com.example.crypto_trading.modal;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;

@Entity
public class Users {
    @Id
    private BigInteger id;

    @Column(name = "username", length = 50)  // VARCHAR(50) for the 'username' field
    private String username;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
