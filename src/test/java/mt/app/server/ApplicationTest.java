package mt.app.server;

import com.codahale.metrics.MetricRegistry;
import mt.app.ApplicationConfiguration;
import mt.app.service.account.AccountService;
import mt.app.service.transfer.TransferService;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ApplicationTest {

	private Application application;

	@Mock
	private TransferService transferService;
	@Mock
	private AccountService accountService;
	@Mock
	private ApplicationConfiguration applicationConfiguration;
	@Mock
	private DbInitializer dbInitializer;
	@Mock
	private MetricRegistry metricRegistry;

	@BeforeClass
	public void setUp() {
		initMocks(this);
		application = new Application(
			transferService,
			accountService,
			applicationConfiguration,
			dbInitializer,
			metricRegistry
		);
	}

	@Test
	public void testStart() throws Exception {
		application.start();

		verify(applicationConfiguration).getPort();
		verify(applicationConfiguration).getMaxThreads();
		verify(applicationConfiguration).getMinThreads();
		verify(applicationConfiguration).getIdleTimeoutMillis();

		verify(dbInitializer).initialize();
	}

}