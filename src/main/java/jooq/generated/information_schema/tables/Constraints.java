/*
 * This file is generated by jOOQ.
*/
package jooq.generated.information_schema.tables;


import javax.annotation.Generated;

import jooq.generated.information_schema.InformationSchema;
import jooq.generated.information_schema.tables.records.ConstraintsRecord;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
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
public class Constraints extends TableImpl<ConstraintsRecord> {

    private static final long serialVersionUID = -1347607331;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.CONSTRAINTS</code>
     */
    public static final Constraints CONSTRAINTS = new Constraints();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ConstraintsRecord> getRecordType() {
        return ConstraintsRecord.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.CONSTRAINTS.CONSTRAINT_CATALOG</code>.
     */
    public final TableField<ConstraintsRecord, String> CONSTRAINT_CATALOG = createField("CONSTRAINT_CATALOG", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.CONSTRAINTS.CONSTRAINT_SCHEMA</code>.
     */
    public final TableField<ConstraintsRecord, String> CONSTRAINT_SCHEMA = createField("CONSTRAINT_SCHEMA", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.CONSTRAINTS.CONSTRAINT_NAME</code>.
     */
    public final TableField<ConstraintsRecord, String> CONSTRAINT_NAME = createField("CONSTRAINT_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.CONSTRAINTS.CONSTRAINT_TYPE</code>.
     */
    public final TableField<ConstraintsRecord, String> CONSTRAINT_TYPE = createField("CONSTRAINT_TYPE", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.CONSTRAINTS.TABLE_CATALOG</code>.
     */
    public final TableField<ConstraintsRecord, String> TABLE_CATALOG = createField("TABLE_CATALOG", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.CONSTRAINTS.TABLE_SCHEMA</code>.
     */
    public final TableField<ConstraintsRecord, String> TABLE_SCHEMA = createField("TABLE_SCHEMA", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.CONSTRAINTS.TABLE_NAME</code>.
     */
    public final TableField<ConstraintsRecord, String> TABLE_NAME = createField("TABLE_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.CONSTRAINTS.UNIQUE_INDEX_NAME</code>.
     */
    public final TableField<ConstraintsRecord, String> UNIQUE_INDEX_NAME = createField("UNIQUE_INDEX_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.CONSTRAINTS.CHECK_EXPRESSION</code>.
     */
    public final TableField<ConstraintsRecord, String> CHECK_EXPRESSION = createField("CHECK_EXPRESSION", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.CONSTRAINTS.COLUMN_LIST</code>.
     */
    public final TableField<ConstraintsRecord, String> COLUMN_LIST = createField("COLUMN_LIST", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.CONSTRAINTS.REMARKS</code>.
     */
    public final TableField<ConstraintsRecord, String> REMARKS = createField("REMARKS", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.CONSTRAINTS.SQL</code>.
     */
    public final TableField<ConstraintsRecord, String> SQL = createField("SQL", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.CONSTRAINTS.ID</code>.
     */
    public final TableField<ConstraintsRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>INFORMATION_SCHEMA.CONSTRAINTS</code> table reference
     */
    public Constraints() {
        this("CONSTRAINTS", null);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.CONSTRAINTS</code> table reference
     */
    public Constraints(String alias) {
        this(alias, CONSTRAINTS);
    }

    private Constraints(String alias, Table<ConstraintsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Constraints(String alias, Table<ConstraintsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Constraints as(String alias) {
        return new Constraints(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Constraints rename(String name) {
        return new Constraints(name, null);
    }
}
