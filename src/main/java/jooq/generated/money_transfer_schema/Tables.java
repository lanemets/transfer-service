/*
 * This file is generated by jOOQ.
*/
package jooq.generated.money_transfer_schema;


import javax.annotation.Generated;

import jooq.generated.money_transfer_schema.tables.Account;
import jooq.generated.money_transfer_schema.tables.AccountBalanceHistory;
import jooq.generated.money_transfer_schema.tables.Balance;
import jooq.generated.money_transfer_schema.tables.Txn;


/**
 * Convenience access to all tables in MONEY_TRANSFER_SCHEMA
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>MONEY_TRANSFER_SCHEMA.ACCOUNT</code>.
     */
    public static final Account ACCOUNT = jooq.generated.money_transfer_schema.tables.Account.ACCOUNT;

    /**
     * The table <code>MONEY_TRANSFER_SCHEMA.TXN</code>.
     */
    public static final Txn TXN = jooq.generated.money_transfer_schema.tables.Txn.TXN;

    /**
     * The table <code>MONEY_TRANSFER_SCHEMA.BALANCE</code>.
     */
    public static final Balance BALANCE = jooq.generated.money_transfer_schema.tables.Balance.BALANCE;

    /**
     * The table <code>MONEY_TRANSFER_SCHEMA.ACCOUNT_BALANCE_HISTORY</code>.
     */
    public static final AccountBalanceHistory ACCOUNT_BALANCE_HISTORY = jooq.generated.money_transfer_schema.tables.AccountBalanceHistory.ACCOUNT_BALANCE_HISTORY;
}