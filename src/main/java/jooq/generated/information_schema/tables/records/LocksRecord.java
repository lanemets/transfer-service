/*
 * This file is generated by jOOQ.
*/
package jooq.generated.information_schema.tables.records;


import javax.annotation.Generated;

import jooq.generated.information_schema.tables.Locks;

import org.jooq.Field;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.TableRecordImpl;


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
public class LocksRecord extends TableRecordImpl<LocksRecord> implements Record4<String, String, Integer, String> {

    private static final long serialVersionUID = 1914172602;

    /**
     * Setter for <code>INFORMATION_SCHEMA.LOCKS.TABLE_SCHEMA</code>.
     */
    public void setTableSchema(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>INFORMATION_SCHEMA.LOCKS.TABLE_SCHEMA</code>.
     */
    public String getTableSchema() {
        return (String) get(0);
    }

    /**
     * Setter for <code>INFORMATION_SCHEMA.LOCKS.TABLE_NAME</code>.
     */
    public void setTableName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>INFORMATION_SCHEMA.LOCKS.TABLE_NAME</code>.
     */
    public String getTableName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>INFORMATION_SCHEMA.LOCKS.SESSION_ID</code>.
     */
    public void setSessionId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>INFORMATION_SCHEMA.LOCKS.SESSION_ID</code>.
     */
    public Integer getSessionId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>INFORMATION_SCHEMA.LOCKS.LOCK_TYPE</code>.
     */
    public void setLockType(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>INFORMATION_SCHEMA.LOCKS.LOCK_TYPE</code>.
     */
    public String getLockType() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<String, String, Integer, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<String, String, Integer, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Locks.LOCKS.TABLE_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Locks.LOCKS.TABLE_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return Locks.LOCKS.SESSION_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Locks.LOCKS.LOCK_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getTableSchema();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getTableName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getSessionId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getLockType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocksRecord value1(String value) {
        setTableSchema(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocksRecord value2(String value) {
        setTableName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocksRecord value3(Integer value) {
        setSessionId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocksRecord value4(String value) {
        setLockType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocksRecord values(String value1, String value2, Integer value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LocksRecord
     */
    public LocksRecord() {
        super(Locks.LOCKS);
    }

    /**
     * Create a detached, initialised LocksRecord
     */
    public LocksRecord(String tableSchema, String tableName, Integer sessionId, String lockType) {
        super(Locks.LOCKS);

        set(0, tableSchema);
        set(1, tableName);
        set(2, sessionId);
        set(3, lockType);
    }
}
