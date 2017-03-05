package mt.app.modules;

import com.google.inject.AbstractModule;

import static mt.app.modules.PropertiesUtils.bindProperties;


public class TestMoneyTransferPropertiesModule extends AbstractModule {
	@Override
	protected void configure() {
		try {
			bindProperties(binder(), "application-test.properties", TestMoneyTransferPropertiesModule.class);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
}
