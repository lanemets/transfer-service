package mt.app.server;

import mt.app.ApplicationConfiguration;
import org.h2.tools.RunScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import static com.google.common.io.Resources.getResource;

@Singleton
public class DbInitializer {

	private static final Logger logger = LoggerFactory.getLogger(DbInitializer.class);

	private final ApplicationConfiguration applicationConfiguration;

	@Inject
	public DbInitializer(ApplicationConfiguration applicationConfiguration) {
		this.applicationConfiguration = applicationConfiguration;
	}

	void initialize() throws SQLException {
		URL scriptFile = getResource(applicationConfiguration.getInitSchemaFileName());
		logger.debug("starting database initializing; script file: {}", scriptFile.getFile());

		RunScript.execute(
			applicationConfiguration.getUrl(),
			applicationConfiguration.getLogin(),
			applicationConfiguration.getPassword(),
			scriptFile.getFile(),
			StandardCharsets.UTF_8,
			false
		);
	}
}
