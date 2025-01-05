CREATE TABLE IF NOT EXISTS USERS (
    id BIGINT PRIMARY KEY,
    username VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS WALLETS (
    id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    currency VARCHAR(10),
    balance DECIMAL(20, 8),
    FOREIGN KEY (user_id) REFERENCES USERS(id)
);

INSERT INTO USERS (id, username) VALUES (1, 'testuser');
INSERT INTO WALLETS (id, user_id, currency, balance) VALUES
(1, 1, 'USDT', 50000.00),
(2, 1, 'BTC', 0.0),
(3, 1, 'ETH', 0.0);
