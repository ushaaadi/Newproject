package utilities;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static Properties properties = new Properties();
	private static String browserType = null;
	//private static ThreadLocal<String> browserType = new ThreadLocal<>();

	static {
		try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("Config.properties")) {
			if (input == null) {
				throw new RuntimeException("Config.properties not found in classpath");
			}
			properties.load(input);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load Config.properties: " + e.getMessage());
		}
	}

	public static String get(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			throw new RuntimeException("Property '" + key + "' not found in Config.properties");
		}
		return value;
	}

	public static void setBrowserType(String browser) {

	//	browserType.set(browser);
		
      browserType = browser;
	}

	public static String getBrowserType() throws Throwable {

	//	String browser = browserType.get();
		if (browserType != null)

			return browserType;
		else
			throw new RuntimeException("browser not specified in the testng.xml");

	}

	public static void removeBrowserType() {
		//browserType.remove();
		;
	}

}