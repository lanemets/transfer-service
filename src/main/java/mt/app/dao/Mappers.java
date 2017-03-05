package mt.app.dao;

import mt.domain.Account;
import org.jooq.Record;
import org.jooq.RecordMapper;

import static jooq.generated.money_transfer_schema.tables.Account.ACCOUNT;
import static jooq.generated.money_transfer_schema.tables.Balance.BALANCE;

class Mappers {

	private Mappers() {
	}

	static RecordMapper<Record, Account> accountMapper() {
		return record -> new Account(
			record.getValue(ACCOUNT.ACCOUNT_ID),
			record.getValue(ACCOUNT.ACCOUNT_INFO),
			record.getValue(BALANCE.ACCOUNT_BALANCE)
		);
	}
}
