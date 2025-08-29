package hooks;

import Base.Driverfactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class hooksjavaclass {

    WebDriver driver;

    @Before(order =0)
    public void setUp() {
        driver = Driverfactory.initializeDriver();
        driver.manage().window().maximize();
    }
    
    @Before (value = "@DatastructuresNavigationTest", order =1)
    public void preCondition_DataStructure() {
    	
    	
    }

    /*@After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    } */
}

	
