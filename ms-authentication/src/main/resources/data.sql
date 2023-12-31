INSERT INTO users (names, username, password, rol) VALUES
('Juan Flores', 'user1', '$2a$10$o2zeBRqFaZMD7shySoDDvO2K6dF6Q1AJzrK3ZEEsRlX7THxBijCwC', 'admin'),
('Juan Robles', 'user2', '$2a$10$o2zeBRqFaZMD7shySoDDvO2K6dF6Q1AJzrK3ZEEsRlX7THxBijCwC', 'customer');

INSERT INTO permissions (uri, roles) VALUES
('/api/exchange-rate/create', '["admin"]'),
('/api/exchange-rate/update', '["admin"]'),
('/api/exchange-rate/list', '["admin"]'),
('/api/exchange-rate/find-by-currency', '["admin", "customer"]'),
('/api/exchange-rate-logs/create', '["admin", "customer"]'),
('/api/exchange-rate-logs/list', '["admin"]'),
('/api/exchange-rate-calculate', '["admin", "customer"]');