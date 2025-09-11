package stepdefinition;

//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.junit.Assert;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import java.time.Duration;
import java.util.LinkedHashMap;
//import java.util.List;
import java.util.Map;

import pageobject.DatastructuresPages;
import pageobject.HomePage;
import pageobject.LoginPageDS;
import Base.Driverfactory;

public class Datastructuresnavigation {

    WebDriver driver;
    WebDriverWait wait;
    DatastructuresPages datastructures;
    HomePage homePage;
	//private Boolean itemFound;
    
    
    
   /* @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        driver.get("https://dsportalapp.herokuapp.com/login");
    }*/

    @When("User logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        LoginPageDS loginPageDS = new LoginPageDS(driver);
        loginPageDS.login("usha.amaravadi@gmail.com", "Numpi3737");
    }

    @Given("User is on {string} page for {string}")
    public void user_is_on_page(String pageName, String pagePath) {
        driver.get("https://dsportalapp.herokuapp.com/" + pagePath);
    }

    @When("User clicks on Data Structures dropdown")
    public void user_clicks_on_data_structures_dropdown() {
        datastructures.clickDropdown();
    }

    @When("User selects {string} from the Data Structures dropdown")
    public void user_selects_module_from_dropdown(String moduleName) {
        datastructures.selectDropdownOption(moduleName);
    }

    @Then("User should be navigated to the {string} module page")
    public void user_should_be_navigated_to_the_module_page(String expectedPath) {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(expectedPath),
            "Expected URL to contain: " + expectedPath + ", but got: " + currentUrl);
    }

    
    
    
    public Datastructuresnavigation() {
        this.driver = Driverfactory.initializeDriver(); 
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.datastructures = new DatastructuresPages(driver); 
        driver.manage().window().maximize();
        this.homePage = new HomePage(driver);
    }

    @Then("User should be able to navigate to all modules in the dropdown")
    public void user_should_navigate_to_all_modules() {
        datastructures.clickDropdown();

        // List of modules and their expected URL paths
        Map<String, String> modules = new LinkedHashMap<>();
        modules.put("Arrays", "arrays");
        modules.put("Linked List", "linked-list");
        modules.put("Stack", "stack");
        modules.put("Queue", "queue");
        modules.put("Tree", "tree");
        modules.put("Graph", "graph");

        for (Map.Entry<String, String> entry : modules.entrySet()) {
            String moduleName = entry.getKey();
            String expectedPath = entry.getValue();

            datastructures.clickDropdown(); // Re-open dropdown after each navigation
            datastructures.selectDropdownOption(moduleName);

            String currentUrl = driver.getCurrentUrl();
            System.out.println("Navigated to: " + currentUrl);
            Assert.assertTrue(currentUrl.contains(expectedPath),
                    "Expected to be on module: " + expectedPath + " but was: " + currentUrl);
        }
    }

    
    
    /* Start comment by Usha on 01 sep 2025
    @When("User selects {string} from the Data Structures dropdown")
    public void user_selects_module_from_dropdown(String moduleName) {
        datastructures.clickDropdown();
        datastructures.selectDropdownOption(moduleName);
    }
    @Then("User should be navigated to the {string} module page")
    public void user_should_be_navigated_to_the_module_page(String expectedPath) {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains(expectedPath),
            "Expected URL to contain: " + expectedPath + ", but got: " + currentUrl);
    }

    @When("User clicks on Data Structures dropdown")
    public void user_clicks_on_data_structures_dropdown() {
        datastructures.clickDropdown();
    }*///Commented By Usha

    //@When("User selects {string} from the Data Structures dropdown")
    //public void user_selects_module_from_dropdown(String moduleName) {
        // Wait for dropdown items to become visible
     
      //     itemFound = homePage.dropDownlist(moduleName);
          
    //}
    
    //@Then("User should be navigated to the {string} module page")
    //public void user_should_be_navigated_to_the_module_page(String expectedPath) {
      
      //  Assert.assertTrue(itemFound,"Expected url endpoint : " + expectedPath );
    //}
};