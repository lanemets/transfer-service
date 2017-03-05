package mt.app.modules;

import com.google.inject.AbstractModule;

import static mt.app.modules.PropertiesUtils.bindProperties;


public class MoneyTransferPropertiesModule extends AbstractModule {

	@Override
	protected void configure() {
		try {
			bindProperties(binder(), "application.properties", MoneyTransferPropertiesModule.class);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
}
