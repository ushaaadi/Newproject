package stepdefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

import pageobject.DatastructuresPages;
import pageobject.HomePage;
import Base.Driverfactory;

public class Datastructuresnavigation {

    WebDriver driver;
    WebDriverWait wait;
    DatastructuresPages datastructures;
    HomePage homePage;
	private Boolean itemFound;
    
    public Datastructuresnavigation() {
        this.driver = Driverfactory.initializeDriver(); 
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.datastructures = new DatastructuresPages(driver); 
        driver.manage().window().maximize();
        this.homePage = new HomePage(driver);
    }

    
    

    @When("User clicks on Data Structures dropdown")
    public void user_clicks_on_data_structures_dropdown() {
        datastructures.clickDropdown();
    }

    @When("User selects {string} from the Data Structures dropdown")
    public void user_selects_module_from_dropdown(String moduleName) {
        // Wait for dropdown items to become visible
     
           itemFound = homePage.dropDownlist(moduleName);
          
    }
    
    @Then("User should be navigated to the {string} module page")
    public void user_should_be_navigated_to_the_module_page(String expectedPath) {
      
        Assert.assertTrue(itemFound,"Expected url endpoint : " + expectedPath );
    }
};