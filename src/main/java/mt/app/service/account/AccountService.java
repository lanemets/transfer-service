package mt.app.service.account;

import mt.app.exceptions.IllegalAccountNumberException;
import mt.domain.Account;

public interface AccountService {

	Account getAccountById(Long accountId) throws IllegalAccountNumberException;

}
