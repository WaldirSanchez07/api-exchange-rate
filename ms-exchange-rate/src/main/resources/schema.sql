CREATE TABLE IF NOT EXISTS exchange_rate(
    id INT AUTO_INCREMENT PRIMARY KEY,
    origin_currency VARCHAR(10),
    fate_currency VARCHAR(10),
    exchange_rate NUMERIC(8, 4),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);