CREATE SCHEMA IF NOT EXISTS money_transfer_schema;

CREATE SEQUENCE IF NOT EXISTS txn_id_seq START WITH 0 INCREMENT BY 1 nomaxvalue;
CREATE SEQUENCE IF NOT EXISTS account_id_seq START WITH 0 INCREMENT BY 1 nomaxvalue;
CREATE SEQUENCE IF NOT EXISTS balance_id_seq START WITH 0 INCREMENT BY 1 nomaxvalue;
CREATE SEQUENCE IF NOT EXISTS account_balance_history_seq START WITH 0 INCREMENT BY 1 nomaxvalue;

CREATE TABLE IF NOT EXISTS money_transfer_schema.account (
  account_id     BIGINT DEFAULT account_id_seq.nextval NOT NULL,
  account_info   VARCHAR(500),

  CONSTRAINT account_id_pk PRIMARY KEY (account_id)
);

CREATE TABLE IF NOT EXISTS money_transfer_schema.txn (
  txn_id       BIGINT IDENTITY,
  from_account BIGINT,
  to_account   BIGINT,
  amount       DECIMAL,
  timestamp    TIMESTAMP,

  FOREIGN KEY (from_account) REFERENCES money_transfer_schema.account (account_id),
  FOREIGN KEY (to_account) REFERENCES money_transfer_schema.account (account_id),

  CONSTRAINT txn_id_pk PRIMARY KEY (txn_id)
);

CREATE TABLE money_transfer_schema.balance (
  balance_id      BIGINT DEFAULT balance_id_seq.nextval NOT NULL,
  account_id      BIGINT,
  account_balance DECIMAL,
  currency_code   VARCHAR(4),

  FOREIGN KEY (account_id) REFERENCES money_transfer_schema.account (account_id),

  CONSTRAINT balance_id_pk PRIMARY KEY (balance_id)
);

CREATE TABLE money_transfer_schema.account_balance_history (
  event_id   BIGINT DEFAULT account_balance_history_seq.nextval NOT NULL,
  account_id BIGINT,
  balance    DECIMAL,
  timestamp  TIMESTAMP,

  CONSTRAINT account_balance_history_pk PRIMARY KEY (event_id)
);