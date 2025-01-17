package mt.app.dao;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;


public class TransferDaoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TransferDao.class).to(TransferDaoImpl.class).in(Scopes.SINGLETON);
	}

}
