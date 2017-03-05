package mt.app.dao;

import mt.domain.Account;
import org.jooq.DSLContext;
import org.jooq.Record;

import javax.inject.Inject;
import java.util.concurrent.atomic.AtomicReference;

import static jooq.generated.money_transfer_schema.tables.Account.ACCOUNT;
import static jooq.generated.money_transfer_schema.tables.Balance.BALANCE;

public class AccountDaoImpl implements AccountDao {

	private final DSLContext dslContext;

	@Inject
	public AccountDaoImpl(DSLContext dslContext) {
		this.dslContext = dslContext;
	}

	@Override
	public Account getAccountById(long accountId) {
		AtomicReference<Record> record = new AtomicReference<>();
		dslContext.transaction(
			configuration -> record.set(
				dslContext
					.select()
					.from(ACCOUNT)
					.join(BALANCE)
					.on(ACCOUNT.ACCOUNT_ID.eq(BALANCE.ACCOUNT_ID))
					.where(ACCOUNT.ACCOUNT_ID.eq(accountId))
					.fetchOne()
			)
		);
		if (null == record.get()) {
			return null;
		}
		return record.get().map(Mappers.accountMapper());
	}

}
