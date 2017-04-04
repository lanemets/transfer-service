package mt.app.server;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Module;
import mt.app.*;
import mt.app.dao.AccountDaoModule;
import mt.app.dao.DatabaseConfigurationModule;
import mt.app.dao.TransferDaoModule;
import mt.app.modules.MetricsModule;
import mt.app.modules.TestMoneyTransferPropertiesModule;
import mt.app.service.account.AccountServiceModule;
import mt.app.service.transfer.TransferServiceModule;
import mt.domain.Account;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.h2.tools.RunScript;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

import static com.google.common.io.Resources.getResource;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static spark.Spark.awaitInitialization;

public class ServerIntegrationTest {

	private final TestServer serverInstance;
	private final HttpClient httpClient;

	public ServerIntegrationTest() {
		serverInstance = new TestServer();
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();

		poolingHttpClientConnectionManager.setMaxTotal(100);
		poolingHttpClientConnectionManager.setDefaultMaxPerRoute(10);

		httpClient = HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager).build();
	}

	@BeforeClass
	public void setUp() throws Exception {
		serverInstance.start();
	}

	@AfterClass
	public void tearDown() throws Exception {
		serverInstance.stop();
	}

	@BeforeMethod
	public void beforeMethod() throws SQLException {
		ApplicationConfiguration applicationConfiguration = getApplicationConfiguration();
		executeScript(
			applicationConfiguration.getInitDataFileName(),
			applicationConfiguration.getUrl(),
			applicationConfiguration.getLogin(),
			applicationConfiguration.getPassword()
		);
	}

	@AfterMethod
	public void afterMethod() throws SQLException {
		ApplicationConfiguration applicationConfiguration = getApplicationConfiguration();
		executeScript(
			applicationConfiguration.getCleanUpDataFileName(),
			applicationConfiguration.getUrl(),
			applicationConfiguration.getLogin(),
			applicationConfiguration.getPassword()
		);
	}

	@Test
	public void transfer() {
		transferRequest(2L, 1L, new BigDecimal("500"));
		Response<Account> accountResponse = getAccountRequest(1L);
		assertEquals(
			accountResponse,
			new Response<>(new Account(1L, "acc1", new BigDecimal("10500")), null)
		);
	}

	@Test(dataProvider = "transferDataProvider")
	public void transferTxnResult(
		String transferUri,
		Response responseExpected
	) {
		HttpUriRequest transferRequest = new HttpPut(transferUri);
		Response<Long> objectFromResponse = requestAndGetObject(transferRequest, new TypeToken<Response<Long>>() {
		}.getType());

		assertEquals(objectFromResponse, responseExpected);
	}

	@DataProvider
	public static Object[][] transferDataProvider() {
		return new Object[][]{
			{
				"http://localhost:9191/transfer/2/1/500",
				new Response<>(1L, null)
			},
			{
				"http://localhost:9191/transfer/4/1/500",
				new Response<>(null, new ErrorResult(ErrorCode.ILLEGAL_ACCOUNT_ID, "invalid account number"))
			},
			{
				"http://localhost:9191/transfer/2/1/21000.00",
				new Response<>(null, new ErrorResult(ErrorCode.NO_ENOUGH_MONEY, "no enough money on accountFrom to withdraw"))
			},
			{
				"http://localhost:9191/transfer/2/1/-210.00",
				new Response<>(null, new ErrorResult(ErrorCode.NEGATIVE_AMOUNT, "negative amount values are not permitted"))
			}
		};
	}

	@Test(dataProvider = "getAccountDataProvider")
	public void getAccount(String uri, Response<Account> responseExpected) {
		HttpGet getAccountRequest = new HttpGet(uri);
		Response<Account> accountResponse = requestAndGetObject(getAccountRequest, new TypeToken<Response<Account>>() {
		}.getType());
		assertEquals(accountResponse, responseExpected);
	}

	@DataProvider
	public static Object[][] getAccountDataProvider() {
		return new Object[][]{
			{
				"http://localhost:9191/account/1",
				new Response<>(new Account(1, "acc1", new BigDecimal("10000")), null)
			},
			{
				"http://localhost:9191/account/101",
				new Response<>(null, new ErrorResult(ErrorCode.ILLEGAL_ACCOUNT_ID, "invalid account number"))
			}
		};
	}

	@Test
	public void concurrentTransferSucceed() {
		Set<Future<Response<Long>>> responseFutures = Sets.newHashSet();
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			responseFutures.add(
				executorService.submit(
					() -> transferRequest(1L, 2L, new BigDecimal("500"))
				)
			);
		}

		responseFutures.forEach(
			responseFuture -> {
				try {
					responseFuture.get(RESPONSE_TIMEOUT, RESPONSE_TIME_UNIT);
				} catch (InterruptedException | ExecutionException | TimeoutException e) {
					e.printStackTrace();
				}
			});

		Response<Account> accountRequest2 = getAccountRequest(2L);
		Response<Account> accountRequest1 = getAccountRequest(1L);

		BigDecimal balance2 = accountRequest2.getResult().getBalance();
		BigDecimal balance1 = accountRequest1.getResult().getBalance();

		assertEquals(balance2.subtract(balance1), new BigDecimal("20000"));
	}

	@Test
	public void twoWaySimultaneouslyTransfer() throws InterruptedException, ExecutionException, TimeoutException {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Response<Long>> responseTransfer = executor.submit(
			() -> transferRequest(1L, 2L, new BigDecimal("500"))
		);

		AtomicReference<Response<Long>> txnId2 = new AtomicReference<>();
		txnId2.set(transferRequest(2L, 1L, new BigDecimal("500")));

		Response<Long> longResponse = responseTransfer.get(RESPONSE_TIMEOUT, RESPONSE_TIME_UNIT);

		assertNotNull(longResponse.getResult());
		assertNotNull(txnId2.get().getResult());

		Response<Account> responseAccount1 = getAccountRequest(1L);
		Response<Account> responseAccount2 = getAccountRequest(2L);

		assertEquals(responseAccount1.getResult().getBalance(), new BigDecimal("10000"));
		assertEquals(responseAccount2.getResult().getBalance(), new BigDecimal("20000"));
	}

	private Response<Account> getAccountRequest(long accountId) {
		HttpGet getAccount = new HttpGet(String.format("http://localhost:9191/account/%s", accountId));
		return requestAndGetObject(
			getAccount,
			new TypeToken<Response<Account>>() {
			}.getType()
		);
	}

	private Response<Long> transferRequest(long accountFrom, long accountTo, BigDecimal amount) {
		HttpUriRequest requestDeposit = new HttpPut(
			String.format("http://localhost:9191/transfer/%s/%s/%s", accountFrom, accountTo, amount)
		);

		return requestAndGetObject(
			requestDeposit,
			new TypeToken<Response<Long>>() {
			}.getType()
		);
	}

	private <T> Response<T> requestAndGetObject(HttpUriRequest request, Type type) {
		HttpResponse execute;
		String responseJson;
		try {
			execute = httpClient.execute(request);
			HttpEntity entity = execute.getEntity();
			responseJson = IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}

		return new Gson().fromJson(responseJson, type);
	}

	private static ApplicationConfiguration getApplicationConfiguration() {
		return InjectorHolder
			.getInstance()
			.getInstance(ApplicationConfiguration.class);
	}

	private static void executeScript(
		String fileName,
		String url,
		String login,
		String password
	) throws SQLException {
		URL scriptFile = getResource(fileName);
		RunScript.execute(
			url,
			login,
			password,
			scriptFile.getFile(),
			StandardCharsets.UTF_8,
			false
		);
	}

	private static final int RESPONSE_TIMEOUT = 10;
	private static final TimeUnit RESPONSE_TIME_UNIT = TimeUnit.SECONDS;

	static class TestServer extends Server {

		@Override
		protected void start() {
			super.start();
			awaitInitialization();
		}

		@Override
		protected Iterable<Module> getModules() {
			return ImmutableList.of(
				new MetricsModule(),
				new TestMoneyTransferPropertiesModule(),
				new TransferServiceModule(),
				new TransferDaoModule(),
				new DatabaseConfigurationModule(),
				new AccountDaoModule(),
				new AccountServiceModule()
			);
		}
	}
}