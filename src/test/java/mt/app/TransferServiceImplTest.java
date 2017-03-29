package mt.app;

import com.google.inject.AbstractModule;
import mt.app.dao.AccountDao;
import mt.app.dao.TransferDao;
import mt.app.exceptions.IllegalAccountNumberException;
import mt.app.exceptions.IllegalAmountException;
import mt.app.exceptions.NoEnoughMoneyException;
import mt.app.service.transfer.TransferService;
import mt.app.service.transfer.TransferServiceModule;
import mt.domain.Account;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@Guice(modules = {
	TransferServiceImplTest.TestModule.class,
	TransferServiceModule.class
})
public class TransferServiceImplTest {

	@Inject
	private TransferService transferService;
	@Inject
	private AccountDao accountDao;
	@Inject
	private TransferDao transferDao;

	@Test
	public void transfer() {
		transferService.transfer(1L, 2L, AMOUNT);

		verify(accountDao).getAccountById(eq(ACCOUNT_FROM.getAccountId()));
		verify(accountDao).getAccountById(eq(ACCOUNT_TO.getAccountId()));

		verify(transferDao).transfer(eq(ACCOUNT_FROM), eq(ACCOUNT_TO), eq(AMOUNT));
	}

	@Test(expectedExceptions = IllegalAccountNumberException.class)
	public void transferAccountDoesNotExist() {
		transferService.transfer(0L, 2L, AMOUNT);
	}

	@Test(expectedExceptions = NoEnoughMoneyException.class)
	public void transferNotEnoughMoney() {
		transferService.transfer(1L, 2L, AMOUNT_EXCEEDED);
	}

	@Test(expectedExceptions = IllegalAmountException.class)
	public void transferIllegalAmount() {
		transferService.transfer(1L, 2L, AMOUNT_EXCEEDED.negate());
	}

	public static class TestModule extends AbstractModule {
		@Override
		protected void configure() {
			AccountDao accountDaoMock = mock(AccountDao.class);
			when(accountDaoMock.getAccountById(1L)).thenReturn(
				new Account(1L, "", new BigDecimal("10000"))
			);

			when(accountDaoMock.getAccountById(2L)).thenReturn(
				new Account(2L, "", new BigDecimal("20000"))
			);

			bind(AccountDao.class).toInstance(accountDaoMock);

			TransferDao transferDaoMock = mock(TransferDao.class);
			when(transferDaoMock.transfer(ACCOUNT_FROM, ACCOUNT_TO, AMOUNT)).thenReturn(TXN_ID);

			bind(TransferDao.class).toInstance(transferDaoMock);
		}
	}

	private static Account ACCOUNT_FROM = new Account(1L, "", new BigDecimal("9500"));
	private static Account ACCOUNT_TO = new Account(2L, "", new BigDecimal("20500"));
	private static BigDecimal AMOUNT = new BigDecimal("500");
	private static final long TXN_ID = 1L;
	private static BigDecimal AMOUNT_EXCEEDED = new BigDecimal("11000");
}