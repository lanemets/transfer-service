package mt.app.modules;

import com.google.inject.Binder;
import com.google.inject.name.Names;

class PropertiesUtils {

	private PropertiesUtils() {
	}

	static <T> void bindProperties(Binder binder, String propertiesFile, Class<T> moduleClazz) throws Exception {
		Names.bindProperties(
			binder,
			PropertiesLoader.loadProperties(propertiesFile, moduleClazz)
		);
	}

}
