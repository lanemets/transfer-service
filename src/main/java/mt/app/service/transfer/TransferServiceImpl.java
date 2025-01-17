package mt.app.service.transfer;

import mt.app.dao.AccountDao;
import mt.app.dao.TransferDao;
import mt.app.exceptions.IllegalAccountNumberException;
import mt.app.exceptions.IllegalAmountException;
import mt.app.exceptions.NoEnoughMoneyException;
import mt.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.math.BigDecimal;

class TransferServiceImpl implements TransferService {

	private static final Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);

	private final TransferDao transferDao;
	private final AccountDao accountDao;
	private final Object lock = new Object();

	@Inject
	public TransferServiceImpl(TransferDao transferDao, AccountDao accountDao) {
		this.transferDao = transferDao;
		this.accountDao = accountDao;
	}

	@Override
	public long transfer(long accountFromId, long accountToId, BigDecimal amount)
		throws NoEnoughMoneyException, IllegalAccountNumberException {

		if (amount.signum() == -1) {
			String message = String.format("illegal sum to withdraw; only positive values are allowed; amount: %s", amount);
			logger.debug(message);
			throw new IllegalAmountException(message);
		}

		synchronized (lock) {
			logger.debug("starting transfer request processing; accountFrom: {}, accountTo: {}", accountFromId, accountToId);

			Account accountFrom = accountDao.getAccountById(accountFromId);
			Account accountTo = accountDao.getAccountById(accountToId);

			if (null == accountFrom || null == accountTo) {
				String errorMessage = String.format(
					"accountFrom or accountTo has not been found: accountFrom: %s, accountTo: %s",
					accountFrom,
					accountTo
				);
				logger.error(errorMessage);
				throw new IllegalAccountNumberException(errorMessage);
			}

			logger.debug("starting money transferring...");
			long txnId = transfer(accountFrom, accountTo, amount, transferDao);

			logger.debug("transferring has succeeded; txn id: {}", txnId);

			return txnId;
		}
	}

	private static long transfer(
		Account accountFrom,
		Account accountTo,
		BigDecimal amount,
		TransferDao transferDao
	) {
		if (-1 == accountFrom.getBalance().compareTo(amount)) {
			String errorMessage = String.format(
				"no enough money on the account for withdrawing; account balance: %s",
				accountFrom.getBalance()
			);
			logger.error(errorMessage);
			throw new NoEnoughMoneyException(errorMessage);
		}

		Account withdraw = accountFrom.withdraw(amount);
		Account deposit = accountTo.deposit(amount);

		long txnId = transferDao.transfer(
			withdraw,
			deposit,
			amount
		);

		return txnId;
	}
}
