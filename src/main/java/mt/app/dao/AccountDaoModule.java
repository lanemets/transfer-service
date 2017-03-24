package mt.app.dao;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class AccountDaoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(AccountDao.class).to(AccountDaoImpl.class).in(Scopes.SINGLETON);
	}
}
