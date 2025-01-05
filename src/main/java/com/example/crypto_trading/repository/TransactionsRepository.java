package com.example.crypto_trading.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crypto_trading.modal.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, BigInteger> {

    List<Transactions> findByUserIdOrderByTimestampDesc(BigInteger userId);
    // You can define custom query methods here if needed
}
