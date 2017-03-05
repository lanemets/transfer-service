package mt.app;

import com.google.common.base.Objects;

public class Response<T> {

	private T result;
	private ErrorResult errorResult;

	public Response(T result, ErrorResult errorResult) {
		this.result = result;
		this.errorResult = errorResult;
	}

	public T getResult() {
		return result;
	}

	public ErrorResult getErrorResult() {
		return errorResult;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Response<?> response = (Response<?>) o;
		return Objects.equal(result, response.result) &&
			Objects.equal(errorResult, response.errorResult);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(result, errorResult);
	}

	@Override
	public String toString() {
		return "Response{" +
			"result=" + result +
			", errorResult=" + errorResult +
			'}';
	}
}
