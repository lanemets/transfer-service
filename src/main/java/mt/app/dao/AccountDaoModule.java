package mt.app.dao;

import com.google.inject.AbstractModule;

public class AccountDaoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(AccountDao.class).to(AccountDaoImpl.class);
	}
}
