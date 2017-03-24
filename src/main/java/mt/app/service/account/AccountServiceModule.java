package mt.app.service.account;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class AccountServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(AccountService.class).to(AccountServiceImpl.class).in(Scopes.SINGLETON);
	}
}
