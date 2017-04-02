package mt.app.server;

import com.codahale.metrics.JmxReporter;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import mt.app.InjectorHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public abstract class Server {

	private final Logger logger = LoggerFactory.getLogger(Server.class);

	protected void start() {
		logger.debug("starting server...");
		Injector injector = InjectorHolder.getInstance();
		if (null == injector) {
			injector = Guice.createInjector(getModules());
			InjectorHolder.setInjector(injector);
		}
		Application instance = injector.getInstance(Application.class);
		try {
			instance.start();

			JmxReporter jmxReporter = injector.getInstance(JmxReporter.class);
			jmxReporter.start();
		} catch (Exception exception) {
			logger.error("ann error has occurred on application startup", exception);
			stop();
		}
	}

	protected void stop() {
		logger.debug("stopping server");
		Spark.stop();
	}

	protected abstract Iterable<Module> getModules();
}
