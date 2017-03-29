package mt.app;

public enum ErrorCode {

	ILLEGAL_ACCOUNT_ID(-2),
	NO_ENOUGH_MONEY(-3),
	NEGATIVE_AMOUNT(-4),
	OTHER(-300);

	private int value;

	ErrorCode(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
