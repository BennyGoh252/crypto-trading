package com.example.crypto_trading.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crypto_trading.modal.Prices;

@Repository
public interface PricesRepository extends JpaRepository<Prices, BigInteger> {

    Prices findTopByOrderByTimestampDesc();
    // You can define custom query methods here if needed
}
