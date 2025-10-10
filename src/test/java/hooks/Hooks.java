package hooks;
import io.cucumber.java.After;
//import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
//import org.testng.Reporter;

import Base.Driverfactory;

public class Hooks {

	public static WebDriver driver;

	@Before(order = 0)
	// @parameter("browser")
	public void setUp() throws IOException {
		String browser = System.getProperty("browser", "firefox"); // default chrome
		System.out.println("Initializing driver with browser: " + browser);
		//String browserName = System.getProperty("browser"); 
	    //if (browserName == null || browserName.isEmpty()) {
	      //  throw new RuntimeException("browser not specified in the testng.xml or System properties");
	    

		//String browserName = ConfigReader.getBrowserType();
		// String browsername = ConfigReader.get("browser");
		// String browserName = System.getProperty("browser", "chrome");
		//Reporter.log("Launching browser: " + browserName, true);
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
