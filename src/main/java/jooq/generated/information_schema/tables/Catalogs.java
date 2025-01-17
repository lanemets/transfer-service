/*
 * This file is generated by jOOQ.
*/
package jooq.generated.information_schema.tables;


import javax.annotation.Generated;

import jooq.generated.information_schema.InformationSchema;
import jooq.generated.information_schema.tables.records.CatalogsRecord;

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
public class Catalogs extends TableImpl<CatalogsRecord> {

    private static final long serialVersionUID = -1955232440;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.CATALOGS</code>
     */
    public static final Catalogs CATALOGS = new Catalogs();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CatalogsRecord> getRecordType() {
        return CatalogsRecord.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.CATALOGS.CATALOG_NAME</code>.
     */
    public final TableField<CatalogsRecord, String> CATALOG_NAME = createField("CATALOG_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

    /**
     * Create a <code>INFORMATION_SCHEMA.CATALOGS</code> table reference
     */
    public Catalogs() {
        this("CATALOGS", null);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.CATALOGS</code> table reference
     */
    public Catalogs(String alias) {
        this(alias, CATALOGS);
    }

    private Catalogs(String alias, Table<CatalogsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Catalogs(String alias, Table<CatalogsRecord> aliased, Field<?>[] parameters) {
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
    public Catalogs as(String alias) {
        return new Catalogs(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Catalogs rename(String name) {
        return new Catalogs(name, null);
    }
}
