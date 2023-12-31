CREATE TABLE IF NOT EXISTS exchange_rate_logs(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    user_names VARCHAR(100),
    origin_currency VARCHAR(10),
    fate_currency VARCHAR(10),
    exchange_rate NUMERIC(8, 4),
    origin_amount NUMERIC(8, 4),
    calculated_amount NUMERIC(8, 4),
    created_at TIMESTAMP
);