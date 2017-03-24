package mt.app.service.transfer;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class TransferServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TransferService.class).to(TransferServiceImpl.class).in(Scopes.SINGLETON);
	}
}
