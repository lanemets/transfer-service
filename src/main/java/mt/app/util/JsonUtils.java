package mt.app.util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonUtils {
	private JsonUtils() {
	}

	private static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	public static ResponseTransformer json() {
		return JsonUtils::toJson;
	}

}
