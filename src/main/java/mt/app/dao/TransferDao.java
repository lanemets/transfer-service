package mt.app.dao;

import mt.domain.Account;

import java.math.BigDecimal;

public interface TransferDao {

	long transfer(Account accountFrom, Account accountTo, BigDecimal amount);
}
