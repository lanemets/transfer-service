package mt.app.dao;

import jooq.generated.money_transfer_schema.tables.records.TxnRecord;
import mt.domain.Account;
import org.jooq.DSLContext;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

import static jooq.generated.money_transfer_schema.tables.AccountBalanceHistory.ACCOUNT_BALANCE_HISTORY;
import static jooq.generated.money_transfer_schema.tables.Balance.BALANCE;
import static jooq.generated.money_transfer_schema.tables.Txn.TXN;


class TransferDaoImpl implements TransferDao {

	private final DSLContext dslContext;

	@Inject
	public TransferDaoImpl(DSLContext dslContext) {
		this.dslContext = dslContext;
	}

	@Override
	public synchronized long transfer(Account accountFrom, Account accountTo, BigDecimal amount) {
		AtomicLong txnId = new AtomicLong();
		dslContext.transaction(
			configuration -> {
				updateBalance(dslContext, accountFrom);
				updateBalance(dslContext, accountTo);

				addToHistory(dslContext, accountFrom);
				addToHistory(dslContext, accountTo);

				txnId.set(createTxn(dslContext, accountFrom, accountTo, amount));
			}
		);
		return txnId.get();
	}

	private static void addToHistory(DSLContext dslContext, Account fromAccount) {
		dslContext
			.insertInto(ACCOUNT_BALANCE_HISTORY)
			.set(ACCOUNT_BALANCE_HISTORY.ACCOUNT_ID, fromAccount.getAccountId())
			.set(ACCOUNT_BALANCE_HISTORY.BALANCE, fromAccount.getBalance())
			.set(ACCOUNT_BALANCE_HISTORY.TIMESTAMP, Timestamp.valueOf(LocalDateTime.now()))
			.execute();
	}

	private static long createTxn(DSLContext dslContext, Account accountFrom, Account accountTo, BigDecimal amount) {
		TxnRecord txnRecord = dslContext
			.insertInto(TXN)
			.set(TXN.FROM_ACCOUNT, accountFrom.getAccountId())
			.set(TXN.TO_ACCOUNT, accountTo.getAccountId())
			.set(TXN.AMOUNT, amount)
			.set(TXN.TIMESTAMP, Timestamp.valueOf(LocalDateTime.now()))
			.returning(TXN.TXN_ID)
			.fetchOne();

		return txnRecord.get(TXN.TXN_ID);
	}

	private static void updateBalance(DSLContext dslContext, Account account) {
		dslContext
			.update(BALANCE)
			.set(BALANCE.ACCOUNT_BALANCE, account.getBalance())
			.where(BALANCE.ACCOUNT_ID.eq(account.getAccountId()))
			.execute();
	}

}
