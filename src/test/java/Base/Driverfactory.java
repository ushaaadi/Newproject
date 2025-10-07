
package Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driverfactory {

	// public static WebDriver driver;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver initializeDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver.set(new SafariDriver());
		} else {
			throw new IllegalArgumentException("Browser not supported: " + browserName);
		}
		driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get().manage().window().maximize();
		return getDriver();
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
			// driver = null;
		}
	}
}
