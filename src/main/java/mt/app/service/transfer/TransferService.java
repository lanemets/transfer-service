package mt.app.service.transfer;

import mt.app.exceptions.IllegalAccountNumberException;
import mt.app.exceptions.NoEnoughMoneyException;

import java.math.BigDecimal;

public interface TransferService {

	long transfer(long accountFrom, long accountTo, BigDecimal amount) throws IllegalAccountNumberException, NoEnoughMoneyException;

}
