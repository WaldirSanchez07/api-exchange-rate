CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    names VARCHAR(100),
    username VARCHAR(40),
    password VARCHAR(190),
    rol VARCHAR(20),
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS permissions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    uri VARCHAR(190),
    roles JSON
);