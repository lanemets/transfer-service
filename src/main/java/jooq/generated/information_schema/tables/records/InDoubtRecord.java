/*
 * This file is generated by jOOQ.
*/
package jooq.generated.information_schema.tables.records;


import javax.annotation.Generated;

import jooq.generated.information_schema.tables.InDoubt;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
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
public class InDoubtRecord extends TableRecordImpl<InDoubtRecord> implements Record2<String, String> {

    private static final long serialVersionUID = 1500046061;

    /**
     * Setter for <code>INFORMATION_SCHEMA.IN_DOUBT.TRANSACTION</code>.
     */
    public void setTransaction(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>INFORMATION_SCHEMA.IN_DOUBT.TRANSACTION</code>.
     */
    public String getTransaction() {
        return (String) get(0);
    }

    /**
     * Setter for <code>INFORMATION_SCHEMA.IN_DOUBT.STATE</code>.
     */
    public void setState(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>INFORMATION_SCHEMA.IN_DOUBT.STATE</code>.
     */
    public String getState() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return InDoubt.IN_DOUBT.TRANSACTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return InDoubt.IN_DOUBT.STATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getTransaction();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InDoubtRecord value1(String value) {
        setTransaction(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InDoubtRecord value2(String value) {
        setState(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InDoubtRecord values(String value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached InDoubtRecord
     */
    public InDoubtRecord() {
        super(InDoubt.IN_DOUBT);
    }

    /**
     * Create a detached, initialised InDoubtRecord
     */
    public InDoubtRecord(String transaction, String state) {
        super(InDoubt.IN_DOUBT);

        set(0, transaction);
        set(1, state);
    }
}
