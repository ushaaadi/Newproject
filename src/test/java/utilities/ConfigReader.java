package utilities;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("Config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties in resources folder.");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error reading config.properties file", ex);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key not found in config.properties: " + key);
        }
        return value.trim();
    }
}

