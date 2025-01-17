/*
 * This file is generated by jOOQ.
*/
package jooq.generated.information_schema.tables;


import javax.annotation.Generated;

import jooq.generated.information_schema.InformationSchema;
import jooq.generated.information_schema.tables.records.IndexesRecord;

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
public class Indexes extends TableImpl<IndexesRecord> {

    private static final long serialVersionUID = 1167516648;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.INDEXES</code>
     */
    public static final Indexes INDEXES = new Indexes();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<IndexesRecord> getRecordType() {
        return IndexesRecord.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.TABLE_CATALOG</code>.
     */
    public final TableField<IndexesRecord, String> TABLE_CATALOG = createField("TABLE_CATALOG", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.TABLE_SCHEMA</code>.
     */
    public final TableField<IndexesRecord, String> TABLE_SCHEMA = createField("TABLE_SCHEMA", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.TABLE_NAME</code>.
     */
    public final TableField<IndexesRecord, String> TABLE_NAME = createField("TABLE_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.NON_UNIQUE</code>.
     */
    public final TableField<IndexesRecord, Boolean> NON_UNIQUE = createField("NON_UNIQUE", org.jooq.impl.SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.INDEX_NAME</code>.
     */
    public final TableField<IndexesRecord, String> INDEX_NAME = createField("INDEX_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.ORDINAL_POSITION</code>.
     */
    public final TableField<IndexesRecord, Short> ORDINAL_POSITION = createField("ORDINAL_POSITION", org.jooq.impl.SQLDataType.SMALLINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.COLUMN_NAME</code>.
     */
    public final TableField<IndexesRecord, String> COLUMN_NAME = createField("COLUMN_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.CARDINALITY</code>.
     */
    public final TableField<IndexesRecord, Integer> CARDINALITY = createField("CARDINALITY", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.PRIMARY_KEY</code>.
     */
    public final TableField<IndexesRecord, Boolean> PRIMARY_KEY = createField("PRIMARY_KEY", org.jooq.impl.SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.INDEX_TYPE_NAME</code>.
     */
    public final TableField<IndexesRecord, String> INDEX_TYPE_NAME = createField("INDEX_TYPE_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.IS_GENERATED</code>.
     */
    public final TableField<IndexesRecord, Boolean> IS_GENERATED = createField("IS_GENERATED", org.jooq.impl.SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.INDEX_TYPE</code>.
     */
    public final TableField<IndexesRecord, Short> INDEX_TYPE = createField("INDEX_TYPE", org.jooq.impl.SQLDataType.SMALLINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.ASC_OR_DESC</code>.
     */
    public final TableField<IndexesRecord, String> ASC_OR_DESC = createField("ASC_OR_DESC", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.PAGES</code>.
     */
    public final TableField<IndexesRecord, Integer> PAGES = createField("PAGES", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.FILTER_CONDITION</code>.
     */
    public final TableField<IndexesRecord, String> FILTER_CONDITION = createField("FILTER_CONDITION", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.REMARKS</code>.
     */
    public final TableField<IndexesRecord, String> REMARKS = createField("REMARKS", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.SQL</code>.
     */
    public final TableField<IndexesRecord, String> SQL = createField("SQL", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.ID</code>.
     */
    public final TableField<IndexesRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.SORT_TYPE</code>.
     */
    public final TableField<IndexesRecord, Integer> SORT_TYPE = createField("SORT_TYPE", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.CONSTRAINT_NAME</code>.
     */
    public final TableField<IndexesRecord, String> CONSTRAINT_NAME = createField("CONSTRAINT_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.INDEXES.INDEX_CLASS</code>.
     */
    public final TableField<IndexesRecord, String> INDEX_CLASS = createField("INDEX_CLASS", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * Create a <code>INFORMATION_SCHEMA.INDEXES</code> table reference
     */
    public Indexes() {
        this("INDEXES", null);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.INDEXES</code> table reference
     */
    public Indexes(String alias) {
        this(alias, INDEXES);
    }

    private Indexes(String alias, Table<IndexesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Indexes(String alias, Table<IndexesRecord> aliased, Field<?>[] parameters) {
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
    public Indexes as(String alias) {
        return new Indexes(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Indexes rename(String name) {
        return new Indexes(name, null);
    }
}
