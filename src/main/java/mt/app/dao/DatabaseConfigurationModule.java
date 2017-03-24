package mt.app.dao;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import mt.app.ApplicationConfiguration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class DatabaseConfigurationModule extends AbstractModule {
	@Override
	protected void configure() {
	}

	@Provides
	@Singleton
	@Inject
	private DataSource connection(ApplicationConfiguration applicationConfiguration) throws SQLException, IOException {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("org.h2.jdbcx.JdbcDataSource");
		hikariConfig.setJdbcUrl(applicationConfiguration.getUrl());
		hikariConfig.setUsername(applicationConfiguration.getLogin());
		hikariConfig.setPassword(applicationConfiguration.getPassword());
		hikariConfig.setConnectionTestQuery("VALUES 1");

		return new HikariDataSource(hikariConfig);
	}

	@Provides
	@Singleton
	@Inject
	private DSLContext dslContext(DataSource dataSource) {
		return DSL.using(dataSource, SQLDialect.H2);
	}
}
