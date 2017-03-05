package mt.app.service.account;

import mt.app.dao.AccountDao;
import mt.app.exceptions.IllegalAccountNumberException;
import mt.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	private final AccountDao accountDao;

	@Inject
	public AccountServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public Account getAccountById(Long accountId) throws IllegalAccountNumberException {
		logger.debug("get account by id; accountId: {}", accountId);
		Account accountById = accountDao.getAccountById(accountId);
		if (null == accountById) {
			logger.error("no account has been found for given id: {}", accountId);
			throw new IllegalAccountNumberException();
		}
		return accountById;
	}

}
