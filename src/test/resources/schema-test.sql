CREATE SCHEMA IF NOT EXISTS money_transfer_schema;

CREATE TABLE IF NOT EXISTS money_transfer_schema.account (
  account_id     BIGINT IDENTITY NOT NULL,
  account_info   VARCHAR(500),

  CONSTRAINT account_id_pk PRIMARY KEY (account_id)
);

CREATE TABLE IF NOT EXISTS money_transfer_schema.txn (
  txn_id       BIGINT IDENTITY NOT NULL,
  from_account BIGINT,
  to_account   BIGINT,
  amount       DECIMAL,
  timestamp    TIMESTAMP,

  FOREIGN KEY (from_account) REFERENCES money_transfer_schema.account (account_id),
  FOREIGN KEY (to_account) REFERENCES money_transfer_schema.account (account_id),

  CONSTRAINT txn_id_pk PRIMARY KEY (txn_id)
);

CREATE TABLE money_transfer_schema.balance (
  balance_id      BIGINT IDENTITY NOT NULL,
  account_id      BIGINT,
  account_balance DECIMAL,
  currency_code   VARCHAR(4),

  FOREIGN KEY (account_id) REFERENCES money_transfer_schema.account (account_id),

  CONSTRAINT balance_id_pk PRIMARY KEY (balance_id)
);

CREATE TABLE money_transfer_schema.account_balance_history (
  event_id   BIGINT IDENTITY NOT NULL,
  account_id BIGINT,
  balance    DECIMAL,
  timestamp  TIMESTAMP,

  CONSTRAINT account_balance_history_pk PRIMARY KEY (event_id)
);