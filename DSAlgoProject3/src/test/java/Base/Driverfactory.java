
package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driverfactory {

	public static WebDriver driver;

	public static WebDriver initializeDriver() {
		// System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		if(driver == null) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		}
		return driver;
	}
}
