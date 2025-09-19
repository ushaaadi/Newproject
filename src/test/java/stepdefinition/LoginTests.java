
package stepdefinition;


import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.LoginPage;
import utilities.ConfigReader;
import utilities.Excelreader;


public class LoginTests extends BaseSteps {
	LoginPage loginpage;
	WebDriverWait wait;
	String excelpath = "src/test/resources/data/Testcase.xlsx";
	String sheetname = "loginscenario";

	@Given("User is on the login page")
	public void userOnLoginPage() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		loginpage = new LoginPage(driver);
		loginpage.openloginpage();
		System.out.println("user is on the loging page");
		
	}

	@When("User enters valid credentials and clicks login button")
	public void userentersvalidcredentials() {
		 Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "login-valid");
		//Map<String, String> data = Excelreader.getTestDataByAction(excelpath,sheetname,"login-valid");
		//loginpage.enterUsername(data.get("username"));
       // loginpage.enterPassword(data.get("password"));
		 System.out.println(data);
        System.out.println("Username is==" +data.get("username"));
        loginpage.clickLoginButton();
	}

	@Then("User should see the {string} heading")
	public void usershouldseeintropage(String expectedHeading) {
		wait.until(ExpectedConditions.visibilityOf(loginpage.getIntroductionHeadingElement()));
		String actualHeading = loginpage.gethomepage();
		assertEquals(actualHeading, expectedHeading, "Heading mismatch!");
	}
	@When("User clicks the {string} link under login button")
	public void userclicksRegisterlink(String linkText) {
		loginpage.clickRegisterLink();
	}

	@Then("User should be redirected to registration page")
	public void usershouldsredirecttoregister() {
		String expectedurl = ConfigReader.get("register.url");
		String actualurl = loginpage.getCurrentUrl();
		Assert.assertTrue(actualurl.contains(expectedurl), "User was not redirected correctly: " + actualurl);
	}

	@When("User clicks login button after leaving the {string} textbox and {string} textbox empty")
	public void usersclickslogin(String textbox1, String textbox2) {
		loginpage.clearusername();
		loginpage.clearpassword();
		loginpage.clickLoginButton();
	}

	@Then("User should see error message {string} below Username textbox")
	public void userseeserrormsg(String expectedmsg) {
		String actualmsg = loginpage.gettextmsg();
		assertEquals(actualmsg, expectedmsg, "Username message mismatch!");
	}
	@When("User clicks login button after entering the {string} and leaves {string} textbox empty")
	public void usersenteredusername(String textbox1, String emptytextbox2) {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "login_empty_pass");
		loginpage.enterUsername(data.get("username"));
		loginpage.clearpassword();
		loginpage.clickLoginButton();
	}

	@Then("User should see error message {string} below Password textbox")
	public void userseeserrorpasswordmsg(String expectedmsg) {
		String actualmsg = loginpage.getvalidationpass();
		assertEquals(actualmsg, expectedmsg, "Password validation message mismatch!");
	}

	@When("User clicks login button after entering the Password only")
	public void userenteredpassword() {
		 Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "login_empty_user");
		loginpage.clearusername();
		loginpage.enterPassword(data.get("password"));
		loginpage.clickLoginButton();
	}

	@When("User clicks login button after entering invalid username and valid password")
	public void userenteredinvalidusername() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "login_invalid_user");
        loginpage.enterUsername(data.get("username"));
        loginpage.enterPassword(data.get("password"));
		//loginpage.enterUsername("userx@graphic.com");
		//loginpage.enterPassword(ConfigReader.get("password"));
		loginpage.clickLoginButton();
	}

	@Then("User should see an error message {string}")
	public void userseeserrorinvalidusermsg(String expectedmsg) {
		String actualmsg = loginpage.getinvalidusererror();
		assertEquals(actualmsg, expectedmsg, "Message mismatch!");
	}

	@When("User clicks login button after entering valid username and invalid password")
	public void userenteredinvalidpassword() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "login_invalid_pass");
        loginpage.enterUsername(data.get("username"));
        loginpage.enterPassword(data.get("password"));
		//loginpage.enterUsername(ConfigReader.get("username"));
		//loginpage.enterPassword("Varlet3737");
		loginpage.clickLoginButton();
	}

	@When("User clicks on dropdown in login page")
	public void userseesdropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(loginpage.getDropdownElement()));
		loginpage.userclicksdropdown();
	}

	@Then("Datastructures dropdown is visible")
	public void dropdownvisible() {
		
		Assert.assertTrue(loginpage.isDropdownVisible(), "Dropdown is not visible!");
	}

	@When("User navigates to Register link on loginpage")
    public void user_navigates_register_link() {
        //loginpage.userclicksdropdown();
       // loginpage.userclicksregisterlink();
    }

    @Then("User should see Register link")
    public void user_should_see_register_link() {
    	
    	Assert.assertTrue(loginpage.isRegisterLinkVisible(),"Register link is not visible on login page!"
    	       );
    	}
    @Given("User is on the home page")
    public void user_on_home_page() {
        driver.get(ConfigReader.get("url")); 
        loginpage = new LoginPage(driver); 
    }
    @When("User navigates to Signin Link")
    public void user_navigates_signin_link() {
     //   loginpage.userclicksdropdown();
       // loginpage.userclickssigninlink();
    	

    	Assert.assertTrue(loginpage.isSigninLinkVisible(), "Signin link is not visible on login page!");
    }
    
    @Then("User should see Signin Link")
    public void user_should_see_signin_link() {
    	Assert.assertTrue(loginpage.isSigninLinkVisible(),"Signin link is not visible on login page!"
    	       );
    	}
    @Then("User should be Logged in successfully")
    public void user_should_be_logged_in_successfully() {
    	wait.until(ExpectedConditions.visibilityOf(loginpage.getSuccessMessageElement()));
        String actualMessage = loginpage.getloggingmsg();
        String expectedMessage = "You are logged in";
        Assert.assertEquals(actualMessage, expectedMessage, "Login success message mismatch!");
    }
   

}

	    
	    

	

	























	
