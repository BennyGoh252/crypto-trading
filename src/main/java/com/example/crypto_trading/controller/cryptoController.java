package com.example.crypto_trading.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crypto_trading.modal.Prices;
import com.example.crypto_trading.modal.Transactions;
import com.example.crypto_trading.modal.Wallets;
import com.example.crypto_trading.repository.PricesRepository;
import com.example.crypto_trading.repository.TransactionsRepository;
import com.example.crypto_trading.repository.WalletRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class cryptoController {
    
    @Autowired
    private PricesRepository pricesRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    // to get the latest best aggregated price
    @GetMapping("/prices/latest")
    public Prices getLatestPrices() {
        return pricesRepository.findTopByOrderByTimestampDesc();
    }

    // to get the wallet balance
    @GetMapping("/wallets/balance")
    public List<Wallets> getWalletBalance(@RequestParam BigInteger userId) {
        return walletRepository.findByUserId(userId);
    }

    // to trade
    @PostMapping("/trade")
    public String trade(@RequestParam BigInteger userId, @RequestParam String pair, @RequestParam String type, @RequestParam BigDecimal amount) {
        Prices latestPrices = pricesRepository.findTopByOrderByTimestampDesc();
        if(latestPrices == null || !latestPrices.getPair().equals(pair)) {
            return "Invalid trading pair or no prices available";
        }

        Wallets usdtWallet = walletRepository.findByUserIdAndCurrency(userId, "USDT");
        Wallets cryptoWallet = walletRepository.findByUserIdAndCurrency(userId, pair.substring(0,3));

        if(type.equalsIgnoreCase("BUY")) {
            BigDecimal cost = latestPrices.getAskPrice().multiply(amount);
            if(usdtWallet.getBalance().compareTo(cost) < 0) {
                return "Insufficient USDT balance";
            }
            usdtWallet.setBalance(usdtWallet.getBalance().subtract(cost));
            cryptoWallet.setBalance(cryptoWallet.getBalance().add(amount));
        } else if(type.equalsIgnoreCase("SELL")) {
            // BigDecimal cost = latestPrices.getAskPrice().multiply(amount);
            if(cryptoWallet.getBalance().compareTo(amount) < 0) {
                return "Insufficient crypto balance";
            }
            BigDecimal proceeds = latestPrices.getBidPrice().multiply(amount);
            usdtWallet.setBalance(usdtWallet.getBalance().add(proceeds));
            cryptoWallet.setBalance(cryptoWallet.getBalance().subtract(amount));
        } else {
            return "Invalid trade type";
        }

        walletRepository.save(usdtWallet);
        walletRepository.save(cryptoWallet);

        Transactions transactions = new Transactions();
        transactions.setUserId(userId);
        transactions.setPair(pair);
        transactions.setType(type);
        transactions.setPrice(type.equalsIgnoreCase("BUY") ? latestPrices.getAskPrice() : latestPrices.getBidPrice());
        transactions.setAmount(amount);
        transactions.setTimestamp(LocalDateTime.now());
        transactionsRepository.save(transactions);

        return "Trade is completed successfully";
    }

    // to get trading history
    @GetMapping("/transactions/history")
    public List<Transactions> getTradingHistory(@RequestParam BigInteger userId) {
        return transactionsRepository.findByUserIdOrderByTimestampDesc(userId);
    }
    
}
