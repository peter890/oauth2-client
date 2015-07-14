/**
 * 
 */
package org.core;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author piotrek
 *
 */
public class Configuration {
	private static Logger logger = LoggerFactory.getLogger(Configuration.class);
	private static Properties appProperties = new Properties();
	private static Configuration instanse = null;

	private Configuration() throws FileNotFoundException {
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream("localConfig.properties");
			appProperties.load(input);
		} catch (Exception e) {
			logger.error("initialize", e);
			throw new FileNotFoundException("Nie znaleziono pliku konfiguracyjnego!");
		}
	}

	public static Configuration getConfiguration() {
		if (instanse == null) {
			try {
				instanse = new Configuration();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return instanse;
	}

	public String getParameterValue(final String parameter) {
		Configuration.getConfiguration();
		return Configuration.appProperties.getProperty(parameter);
	}

}