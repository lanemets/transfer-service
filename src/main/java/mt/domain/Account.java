package mt.domain;

import com.google.common.base.Objects;

import java.math.BigDecimal;

public class Account {
	private final long accountId;
	private final String accountInfo;
	private final BigDecimal balance;

	public Account(long accountId, String accountInfo, BigDecimal balance) {
		this.accountId = accountId;
		this.balance = balance;
		this.accountInfo = accountInfo;
	}

	public Account withdraw(BigDecimal amount) {
		return new Account(
			this.accountId,
			this.getAccountInfo(),
			balance.subtract(amount)
		);
	}

	public Account deposit(BigDecimal amount) {
		return new Account(
			this.accountId,
			this.getAccountInfo(),
			balance.add(amount)
		);
	}

	public long getAccountId() {
		return accountId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public String getAccountInfo() {
		return accountInfo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Account account = (Account) o;
		return accountId == account.accountId &&
			Objects.equal(accountInfo, account.accountInfo) &&
			Objects.equal(balance, account.balance);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(accountId, accountInfo, balance);
	}

	@Override
	public String toString() {
		return "Account{" +
			"accountId=" + accountId +
			", accountInfo='" + accountInfo + '\'' +
			", balance=" + balance +
			'}';
	}
}
