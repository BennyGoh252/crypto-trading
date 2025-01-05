package com.example.crypto_trading.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crypto_trading.modal.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, BigInteger> {
    // You can define custom query methods here if needed
}
