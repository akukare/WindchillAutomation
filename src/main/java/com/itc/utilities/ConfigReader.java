package com.itc.utilities;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConfigReader {
	private static Properties prop;
	private static boolean isLoaded = false;
	public static long implicitwait=10;
	
	    public static Properties getProperties() {
	        if (prop == null) {
	            prop = new Properties();
	            try {
	                FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
	                prop.load(fis);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return prop;
	    }
	    

/**
 * @name loadConfig
 * @description loads or reads the config file
 * @param
 * @author Rengesh
 * @return void
 */
public static void loadConfig() throws Exception {
	String fileName = System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties";
	try {
		if (isLoaded)
			return;

		synchronized (ConfigReader.class) {
			if (isLoaded)
				return;

			InputStream input = new FileInputStream(fileName);
			prop.load(input);
			isLoaded = true;
		}
	} catch (Exception e) {
		throw new RuntimeException("Failed to load config.properties", e);
	}
}

/**
 * @name get
 * @description gets the value from config using key
 * @param key
 * @author Rengesh
 * @return value
 */
public static String get(String key) throws Exception {

	loadConfig();
	String value = prop.getProperty(key);
	if (value == null || value.trim().isEmpty())
		throw new RuntimeException("Missing config value for key: " + key);

	return value.trim();
}

}