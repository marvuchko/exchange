CREATE TABLE IF NOT EXISTS system_user (
    id UUID PRIMARY KEY,
    first_name VARCHAR(120) NOT NULL,
    last_name VARCHAR(120) NOT NULL,
    email VARCHAR(255) NOT NULL
);