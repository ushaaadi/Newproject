package hooks;

import Base.Driverfactory;
import io.cucumber.java.After;
//import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.ConfigReader;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class Hooks {

    WebDriver driver;

    @Before(order =0)
   // @parameter("browser")
    public void setUp() throws Throwable {
    	String browserName = ConfigReader.getBrowserType();
    	//String browsername = ConfigReader.get("browser");
    	// String browserName = System.getProperty("browser", "chrome");
    	 Reporter.log("Launching browser: " + browserName, true); 
        driver = Driverfactory.initializeDriver(browserName);
        driver.manage().window().maximize();
        
    }
    
    @Before (value = "@@DatastructuresTest", order =1)
    public void preCondition_DataStructure() {
    	
    	
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    } 
}

	
