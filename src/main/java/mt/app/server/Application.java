package mt.app.server;

import mt.app.ApplicationConfiguration;
import mt.app.ErrorCode;
import mt.app.ErrorResult;
import mt.app.Response;
import mt.app.exceptions.DatabaseInitializationException;
import mt.app.exceptions.IllegalAccountNumberException;
import mt.app.exceptions.NoEnoughMoneyException;
import mt.app.service.account.AccountService;
import mt.app.service.transfer.TransferService;
import mt.domain.Account;
import org.h2.tools.RunScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.google.common.io.Resources.getResource;
import static mt.app.util.JsonUtills.json;
import static spark.Spark.*;

@Singleton
class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	private final TransferService transferService;
	private final AccountService accountService;
	private final ApplicationConfiguration applicationConfiguration;

	@Inject
	Application(
		TransferService transferService,
		AccountService accountService, ApplicationConfiguration applicationConfiguration
	) {
		this.transferService = transferService;
		this.accountService = accountService;
		this.applicationConfiguration = applicationConfiguration;
	}

	void start() throws DatabaseInitializationException {
		logger.debug("starting application...");

		initDb(applicationConfiguration);

		port(applicationConfiguration.getPort());

		threadPool(
			applicationConfiguration.getMaxThreads(),
			applicationConfiguration.getMinThreads(),
			applicationConfiguration.getIdleTimeoutMillis()
		);
		put("/transfer/:from/:to/:amount", (request, response) -> {
			try {
				long txnId = transferService.transfer(
					Long.valueOf(request.params(":from")),
					Long.valueOf(request.params(":to")),
					new BigDecimal(request.params(":amount"))
				);
				return new Response<>(txnId, null);
			} catch (IllegalAccountNumberException exception) {
				String errorMessage = String.format("invalid account numbers: %d, %d",
					Long.valueOf(request.params(":from")),
					Long.valueOf(request.params(":to")));
				logger.error(errorMessage, exception);
				return new Response<>(null, new ErrorResult(ErrorCode.ILLEGAL_ACCOUNT_ID, errorMessage));
			} catch (NoEnoughMoneyException exception) {
				String errorMessage = "no enough money on accountFrom to withdraw";
				logger.error(errorMessage, exception);
				return new Response<>(null, new ErrorResult(ErrorCode.NO_ENOUGH_MONEY, errorMessage));
			} catch (Exception exception) {
				String errorMessage = "unexpected exception has occurred";
				logger.error(errorMessage, exception);
				return new Response<>(null, new ErrorResult(ErrorCode.OTHER, errorMessage));
			}
		}, json());

		get("/account/:id", (request, response) -> {
			try {
				Account accountById = accountService.getAccountById(Long.valueOf(request.params(":id")));
				return new Response<>(accountById, null);
			} catch (IllegalAccountNumberException exception) {
				String errorMessage = String.format("invalid account number: %d", Long.valueOf(request.params(":id")));
				logger.error(errorMessage, exception);
				return new Response<>(null, new ErrorResult(ErrorCode.ILLEGAL_ACCOUNT_ID, errorMessage));
			} catch (Exception exception) {
				String errorMessage = "unexpected exception has occurred";
				logger.error(errorMessage, exception);
				return new Response<>(null, new ErrorResult(ErrorCode.OTHER, errorMessage));
			}
		}, json());

		logger.debug("started on port: {}", applicationConfiguration.getPort());
	}

	private static void initDb(ApplicationConfiguration applicationConfiguration) throws DatabaseInitializationException {
		try {
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
		} catch (Exception exception) {
			logger.error("an error has occurred during init script execution. Unable to proceed", exception);
			throw new DatabaseInitializationException();
		}
	}
}
