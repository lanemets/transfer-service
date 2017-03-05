package mt.app;

import com.google.inject.Singleton;

import javax.inject.Inject;
import javax.inject.Named;

@Singleton
public class ApplicationConfiguration {

	@Inject
	@Named("mt.jdbc.url")
	private String url;

	@Inject
	@Named("mt.db.init.schema.filename")
	private String initSchemaFileName;

	@Inject
	@Named("mt.db.init.data.filename")
	private String initDataFileName;

	@Inject
	@Named("mt.db.cleanup.data.filename")
	private String cleanUpDataFileName;

	@Inject
	@Named("mt.jdbc.login")
	private String login;

	@Inject
	@Named("mt.jdbc.password")
	private String password;

	@Inject
	@Named("mt.input.schema.name")
	private String inputSchemaName;

	@Inject
	@Named("mt.output.schema.name")
	private String outputSchemaName;

	@Inject
	@Named("mt.server.port")
	private Integer port;

	@Inject
	@Named("mt.server.max.threads")
	private int maxThreads;

	@Inject
	@Named("mt.server.min.threads")
	private int minThreads;

	@Inject
	@Named("mt.server.idle.timeout.millis")
	private int idleTimeoutMillis;

	public ApplicationConfiguration() {
	}

	public String getUrl() {
		return url;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getInputSchemaName() {
		return inputSchemaName;
	}

	public String getOutputSchemaName() {
		return outputSchemaName;
	}

	public Integer getPort() {
		return port;
	}

	public int getMaxThreads() {
		return maxThreads;
	}

	public int getMinThreads() {
		return minThreads;
	}

	public int getIdleTimeoutMillis() {
		return idleTimeoutMillis;
	}

	public String getInitSchemaFileName() {
		return initSchemaFileName;
	}

	public String getInitDataFileName() {
		return initDataFileName;
	}

	public String getCleanUpDataFileName() {
		return cleanUpDataFileName;
	}
}
