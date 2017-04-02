package mt.app.server;

import com.codahale.metrics.MetricRegistry;
import mt.app.ApplicationConfiguration;
import mt.app.ErrorCode;
import mt.app.ErrorResult;
import mt.app.Response;
import mt.app.exceptions.DatabaseInitializationException;
import mt.app.exceptions.IllegalAccountNumberException;
import mt.app.exceptions.IllegalAmountException;
import mt.app.exceptions.NoEnoughMoneyException;
import mt.app.service.account.AccountService;
import mt.app.service.transfer.TransferService;
import mt.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;

import static mt.app.util.JsonUtils.json;
import static spark.Spark.*;

@Singleton
class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	private final TransferService transferService;
	private final AccountService accountService;
	private final ApplicationConfiguration applicationConfiguration;
	private final DbInitializer dbInitializer;
	private final MetricRegistry metricRegistry;

	@Inject
	Application(
		TransferService transferService,
		AccountService accountService,
		ApplicationConfiguration applicationConfiguration,
		DbInitializer dbInitializer,
		MetricRegistry metricRegistry
	) {
		this.transferService = transferService;
		this.accountService = accountService;
		this.applicationConfiguration = applicationConfiguration;
		this.dbInitializer = dbInitializer;
		this.metricRegistry = metricRegistry;
	}

	void start() throws DatabaseInitializationException {
		logger.debug("starting application...");

		initDb(dbInitializer, metricRegistry);

		int port = applicationConfiguration.getPort();
		port(port);

		threadPool(
			applicationConfiguration.getMaxThreads(),
			applicationConfiguration.getMinThreads(),
			applicationConfiguration.getIdleTimeoutMillis()
		);

		put("/transfer/:from/:to/:amount", (request, response) -> {
			try {
				BigDecimal amount = new BigDecimal(request.params(":amount"));
				long accountFrom = Long.valueOf(request.params(":from"));
				long accountTo = Long.valueOf(request.params(":to"));

				long txnId = transferService.transfer(
					accountFrom,
					accountTo,
					amount
				);

				reportCounter(metricRegistry, "transfer_success");
				return new Response<>(txnId, null);
			} catch (IllegalAccountNumberException exception) {
				String errorMessage = "invalid account number";
				logger.error(errorMessage, exception);

				reportCounter(metricRegistry, "transfer_err_illegal_account");
				return new Response<>(null, new ErrorResult(ErrorCode.ILLEGAL_ACCOUNT_ID, errorMessage));
			} catch (IllegalAmountException exception) {
				String errorMessage = "negative amount values are not permitted";
				logger.error(errorMessage, exception);

				reportCounter(metricRegistry, "transfer_negative_amount");
				return new Response<>(null, new ErrorResult(ErrorCode.NEGATIVE_AMOUNT, errorMessage));
			} catch (NoEnoughMoneyException exception) {
				String errorMessage = "no enough money on accountFrom to withdraw";
				logger.error(errorMessage, exception);

				reportCounter(metricRegistry, "transfer_err_no_money");
				return new Response<>(null, new ErrorResult(ErrorCode.NO_ENOUGH_MONEY, errorMessage));
			} catch (Exception exception) {
				String errorMessage = "unexpected exception has occurred";
				logger.error(errorMessage, exception);

				reportCounter(metricRegistry, "transfer_err_other");
				return new Response<>(null, new ErrorResult(ErrorCode.OTHER, errorMessage));
			}
		}, json());

		get("/account/:id", (request, response) -> {
			try {
				Account accountById = accountService.getAccountById(Long.valueOf(request.params(":id")));

				reportCounter(metricRegistry, "get_account_success");
				return new Response<>(accountById, null);
			} catch (IllegalAccountNumberException exception) {
				String errorMessage = "invalid account number";
				logger.error(errorMessage, exception);

				reportCounter(metricRegistry, "get_account_err_illegal_account");
				return new Response<>(null, new ErrorResult(ErrorCode.ILLEGAL_ACCOUNT_ID, errorMessage));
			} catch (Exception exception) {
				String errorMessage = "unexpected exception has occurred";
				logger.error(errorMessage, exception);

				reportCounter(metricRegistry, "get_account_err_other");
				return new Response<>(null, new ErrorResult(ErrorCode.OTHER, errorMessage));
			}
		}, json());

		logger.debug("started on port: {}", port);
	}

	private static void initDb(
		DbInitializer dbInitializer,
		MetricRegistry metricRegistry
	) throws DatabaseInitializationException {
		try {
			dbInitializer.initialize();
		} catch (Exception exception) {
			String message = "an error has occurred during init script execution. Unable to proceed";
			logger.error(message, exception);

			reportCounter(metricRegistry, "db_init_error");
			throw new DatabaseInitializationException(message, exception);
		}
	}

	private static void reportCounter(MetricRegistry metricRegistry, String counterName) {
		metricRegistry.counter(counterName).inc();
	}
}
