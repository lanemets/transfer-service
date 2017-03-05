SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE money_transfer_schema.account_balance_history;
TRUNCATE TABLE money_transfer_schema.balance;
TRUNCATE TABLE money_transfer_schema.txn;
TRUNCATE TABLE money_transfer_schema.account;

ALTER TABLE money_transfer_schema.account_balance_history ALTER COLUMN event_id RESTART WITH 1;
ALTER TABLE money_transfer_schema.balance ALTER COLUMN balance_id RESTART WITH 1;
ALTER TABLE money_transfer_schema.txn ALTER COLUMN txn_id RESTART WITH 1;
ALTER TABLE money_transfer_schema.account ALTER COLUMN account_id RESTART WITH 1;