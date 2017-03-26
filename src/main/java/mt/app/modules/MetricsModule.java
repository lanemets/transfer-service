package mt.app.modules;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

public class MetricsModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(JmxReporter.class).toProvider(JmxReporterProvider.class).in(Scopes.SINGLETON);
	}

	@Provides
	@Singleton
	MetricRegistry metricRegistry() {
		return new MetricRegistry();
	}

	static class JmxReporterProvider implements Provider<JmxReporter> {

		private final MetricRegistry metricRegistry;

		@Inject
		public JmxReporterProvider(MetricRegistry metricRegistry) {
			this.metricRegistry = metricRegistry;
		}

		@Override
		public JmxReporter get() {
			return JmxReporter.forRegistry(metricRegistry).inDomain("mt.transfer.app").build();
		}
	}
}