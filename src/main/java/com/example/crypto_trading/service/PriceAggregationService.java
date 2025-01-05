package com.example.crypto_trading.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.crypto_trading.modal.Prices;
import com.example.crypto_trading.repository.PricesRepository;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class PriceAggregationService {
    private static final String binanceUrl = "https://api.binance.com/api/v3/ticker/bookTicker";
    private static final String huoBiUrl = "https://api.huobi.pro/market/tickers";

    @Autowired
    private PricesRepository pricesRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 10000)
    public void aggregatePrices() {
        try {
            BigDecimal binanceBid = getBinancePrice("bid");
            BigDecimal binanceAsk = getBinancePrice("ask");
            BigDecimal huoBiBid = getHuoBiPrice("bid");
            BigDecimal huoBiAsk = getHuoBiPrice("ask");

            BigDecimal bestBid = binanceBid.max(huoBiBid);
            BigDecimal bestAsk = binanceAsk.max(huoBiAsk);

            Prices price = new Prices();
            price.setPair("BTCUSDT");
            price.setBidPrice(bestBid);
            price.setAskPrice(bestAsk);
            price.setTimestamp(LocalDateTime.now());

            pricesRepository.save(price);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BigDecimal getBinancePrice(String type) {
        JsonNode response = restTemplate.getForObject(binanceUrl, JsonNode.class);
        if(response != null && response .has("symbol") && response.get("symbol").asText().equals("BTCUSDT")) {
            return new BigDecimal(response.get(type.equals("bid") ? "bidPrice" : "askPrice").asText());
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal getHuoBiPrice(String type) {
        JsonNode response = restTemplate.getForObject(huoBiUrl, JsonNode.class);
        if(response != null && response.has("data")) {
            for(JsonNode ticker: response.get("data")) {
                if(ticker.has("symbol") && ticker.get("symbol").asText().equals("btcusdt")) {
                    return new BigDecimal(ticker.get(type.equals("bid") ? "bid" : "ask").asText());
                }
            }
        }
        return BigDecimal.ZERO;
    }
}
