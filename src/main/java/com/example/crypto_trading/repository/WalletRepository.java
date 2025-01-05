package com.example.crypto_trading.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crypto_trading.modal.Wallets;

@Repository
public interface WalletRepository extends JpaRepository<Wallets, BigInteger> {

    List<Wallets> findByUserId(BigInteger userId);
    // You can define custom query methods here if needed

    Wallets findByUserIdAndCurrency(BigInteger userId, String string);
}
