package mt.app.service.transfer;

import mt.app.dao.AccountDao;
import mt.app.dao.TransferDao;
import mt.app.exceptions.IllegalAccountNumberException;
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

	@Inject
	public TransferServiceImpl(TransferDao transferDao, AccountDao accountDao) {
		this.transferDao = transferDao;
		this.accountDao = accountDao;
	}

	@Override
	public synchronized long transfer(long accountFromId, long accountToId, BigDecimal amount)
		throws NoEnoughMoneyException, IllegalAccountNumberException {

		logger.debug("starting transfer request processing; accountFrom: {}, accountTo: {}", accountFromId, accountToId);

		Account accountFrom = accountDao.getAccountById(accountFromId);
		Account accountTo = accountDao.getAccountById(accountToId);

		if (null == accountFrom || null == accountTo) {
			logger.error("accountFrom or accountTo has not been found: accountFrom: {}, accountTo: {}", accountFrom, accountTo);
			throw new IllegalAccountNumberException();
		}

		logger.debug("starting money transferring...");
		long txnId = transfer(accountFrom, accountTo, amount, transferDao);

		logger.debug("transferring has succeeded; txn id: {}", txnId);

		return txnId;
	}

	private static long transfer(
		Account accountFrom,
		Account accountTo,
		BigDecimal amount,
		TransferDao transferDao
	) {
		if (-1 == accountFrom.getBalance().compareTo(amount)) {
			logger.error("no enough money on the account for withdrawing; account balance: {}", accountFrom.getBalance());
			throw new NoEnoughMoneyException();
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
