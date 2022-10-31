CREATE TABLE IF NOT EXISTS user_wallet (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    balance DECIMAL(10, 4) NOT NULL DEFAULT 0,
    currency BIGINT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS trade_currency (
    id UUID PRIMARY KEY,
    name VARCHAR(120)
);

CREATE TABLE IF NOT EXISTS trade_pair (
    id UUID PRIMARY KEY,
    first_trade_currency_id UUID,
    second_trade_currency_id UUID
);

ALTER TABLE wallet.trade_pair
ADD CONSTRAINT fk_first_trade_currency FOREIGN KEY(first_trade_currency_id)
REFERENCES wallet.trade_currency(id);

ALTER TABLE wallet.trade_pair
ADD CONSTRAINT fk_second_trade_currency FOREIGN KEY(second_trade_currency_id)
REFERENCES wallet.trade_currency(id);