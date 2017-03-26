package mt.app.service.account;

import com.google.inject.AbstractModule;
import mt.app.dao.AccountDao;
import mt.app.exceptions.IllegalAccountNumberException;
import mt.domain.Account;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@Guice(modules = {
	AccountServiceModule.class,
	AccountServiceImplTest.TestModule.class
})
public class AccountServiceImplTest {

	@Inject
	private AccountService accountService;
	@Inject
	private AccountDao accountDao;

	@Test
	public void getAccount() {
		accountService.getAccountById(ACCOUNT_ID);
		verify(accountDao).getAccountById(eq(ACCOUNT_ID));
	}

	@Test(expectedExceptions = IllegalAccountNumberException.class, expectedExceptionsMessageRegExp = "no account has been found for given id: \\d{1}")
	public void getAccountDoesNotExist() {
		accountService.getAccountById(ACCOUNT_NOT_EXISTED);
	}

	public static class TestModule extends AbstractModule {

		@Override
		protected void configure() {
			AccountDao accountDaoMock = mock(AccountDao.class);
			when(accountDaoMock.getAccountById(ACCOUNT_ID)).thenReturn(ACCOUNT);

			bind(AccountDao.class).toInstance(accountDaoMock);
		}
	}

	private static Account ACCOUNT = new Account(1L, "", new BigDecimal("9500"));
	private static final long ACCOUNT_ID = 1L;
	private static final long ACCOUNT_NOT_EXISTED = 2L;
}