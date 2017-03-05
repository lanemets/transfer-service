package mt.app.modules;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

class PropertiesLoader {
	private static final Logger log = LoggerFactory.getLogger(PropertiesLoader.class);

	static Properties loadProperties(String propertyFileName, Class<?> clazz) throws Exception {
		String fileName = System.getProperty(propertyFileName);
		Properties properties = new Properties();

		try {
			Object e;
			if (!StringUtils.isEmpty(fileName)) {
				log.debug("loading properties from file; properties: {}, class: {}, file: {}", propertyFileName, clazz.getSimpleName(), fileName);
				e = new FileInputStream(fileName);
			} else {
				log.debug("loading properties from resource; properties: {}, class: {}, file: {}", propertyFileName, clazz.getSimpleName(), fileName);
				ClassLoader loader = clazz.getClassLoader();
				URL url = loader.getResource(propertyFileName);
				assert null != url;
				e = url.openStream();
			}

			properties.load((InputStream) e);
			return properties;
		} catch (IOException exception) {
			throw new IllegalStateException(String.format("config loading error; properties file: %s", propertyFileName), exception);
		}
	}

}
