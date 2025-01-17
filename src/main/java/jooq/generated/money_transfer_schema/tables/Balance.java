/*
 * This file is generated by jOOQ.
*/
package jooq.generated.money_transfer_schema.tables;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import jooq.generated.money_transfer_schema.Keys;
import jooq.generated.money_transfer_schema.MoneyTransferSchema;
import jooq.generated.money_transfer_schema.tables.records.BalanceRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Balance extends TableImpl<BalanceRecord> {

    private static final long serialVersionUID = -900456086;

    /**
     * The reference instance of <code>MONEY_TRANSFER_SCHEMA.BALANCE</code>
     */
    public static final Balance BALANCE = new Balance();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BalanceRecord> getRecordType() {
        return BalanceRecord.class;
    }

    /**
     * The column <code>MONEY_TRANSFER_SCHEMA.BALANCE.BALANCE_ID</code>.
     */
    public final TableField<BalanceRecord, Long> BALANCE_ID = createField("BALANCE_ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("(NEXT VALUE FOR MONEY_TRANSFER_SCHEMA.SYSTEM_SEQUENCE_3465FFDE_162D_430E_A187_AB06A5EA8D5F)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>MONEY_TRANSFER_SCHEMA.BALANCE.ACCOUNT_ID</code>.
     */
    public final TableField<BalanceRecord, Long> ACCOUNT_ID = createField("ACCOUNT_ID", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>MONEY_TRANSFER_SCHEMA.BALANCE.ACCOUNT_BALANCE</code>.
     */
    public final TableField<BalanceRecord, BigDecimal> ACCOUNT_BALANCE = createField("ACCOUNT_BALANCE", org.jooq.impl.SQLDataType.DECIMAL.precision(65535, 32767), this, "");

    /**
     * The column <code>MONEY_TRANSFER_SCHEMA.BALANCE.CURRENCY_CODE</code>.
     */
    public final TableField<BalanceRecord, String> CURRENCY_CODE = createField("CURRENCY_CODE", org.jooq.impl.SQLDataType.VARCHAR.length(4), this, "");

    /**
     * Create a <code>MONEY_TRANSFER_SCHEMA.BALANCE</code> table reference
     */
    public Balance() {
        this("BALANCE", null);
    }

    /**
     * Create an aliased <code>MONEY_TRANSFER_SCHEMA.BALANCE</code> table reference
     */
    public Balance(String alias) {
        this(alias, BALANCE);
    }

    private Balance(String alias, Table<BalanceRecord> aliased) {
        this(alias, aliased, null);
    }

    private Balance(String alias, Table<BalanceRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return MoneyTransferSchema.MONEY_TRANSFER_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<BalanceRecord, Long> getIdentity() {
        return Keys.IDENTITY_BALANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<BalanceRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_16;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<BalanceRecord>> getKeys() {
        return Arrays.<UniqueKey<BalanceRecord>>asList(Keys.CONSTRAINT_16);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<BalanceRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<BalanceRecord, ?>>asList(Keys.CONSTRAINT_169);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Balance as(String alias) {
        return new Balance(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Balance rename(String name) {
        return new Balance(name, null);
    }
}
