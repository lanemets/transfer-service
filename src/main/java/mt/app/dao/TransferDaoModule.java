package mt.app.dao;

import com.google.inject.AbstractModule;


public class TransferDaoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TransferDao.class).to(TransferDaoImpl.class);
	}

}
