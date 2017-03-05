package mt.app.service.account;

import com.google.inject.AbstractModule;

public class AccountServiceModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(AccountService.class).to(AccountServiceImpl.class);
	}
}
