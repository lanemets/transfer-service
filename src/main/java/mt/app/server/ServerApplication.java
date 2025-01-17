package mt.app.server;

import com.google.common.collect.ImmutableList;
import com.google.inject.Module;
import mt.app.dao.AccountDaoModule;
import mt.app.dao.DatabaseConfigurationModule;
import mt.app.dao.TransferDaoModule;
import mt.app.modules.MetricsModule;
import mt.app.modules.MoneyTransferPropertiesModule;
import mt.app.service.account.AccountServiceModule;
import mt.app.service.transfer.TransferServiceModule;

import java.sql.SQLException;

public class ServerApplication extends Server {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		new ServerApplication().start();
	}

	@Override
	protected Iterable<Module> getModules() {
		return ImmutableList.of(
			new MetricsModule(),
			new MoneyTransferPropertiesModule(),
			new TransferServiceModule(),
			new DatabaseConfigurationModule(),
			new AccountDaoModule(),
			new TransferDaoModule(),
			new AccountServiceModule()
		);
	}
}
