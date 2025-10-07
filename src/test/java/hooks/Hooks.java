package hooks;


import io.cucumber.java.After;
//import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
//import org.testng.Reporter;

import Base.Driverfactory;

import java.io.IOException;

public class Hooks {

	public static WebDriver driver;

	@Before(order = 0)
	public void setUp() throws IOException {
		// Read browser from system property
		String browser = System.getProperty("browser", "chrome"); // default chrome
		System.out.println("Initializing driver with browser: " + browser);

		// Initialize driver
		driver = Driverfactory.initializeDriver(browser);
		driver.manage().window().maximize();
	}
	@Before(value = "@@DatastructuresTest", order = 1)
	public void preCondition_DataStructure() {

	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
