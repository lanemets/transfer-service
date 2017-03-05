package mt.app;

import com.google.common.base.Objects;

public class ErrorResult {

	private ErrorCode errorCode;
	private String errorDescription;

	public ErrorResult(ErrorCode errorCode, String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ErrorResult that = (ErrorResult) o;
		return errorCode == that.errorCode &&
			Objects.equal(errorDescription, that.errorDescription);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(errorCode, errorDescription);
	}
}
