package mt.app.service.transfer;

import com.google.inject.AbstractModule;

public class TransferServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TransferService.class).to(TransferServiceImpl.class);
	}
}
