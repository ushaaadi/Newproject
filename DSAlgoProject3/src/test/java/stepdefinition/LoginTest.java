
package stepdefinition;


import static org.testng.Assert.assertEquals;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Base.Driverfactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.LoginPage;


public class LoginTest {
	WebDriver driver; 
	LoginPage loginpage;
	WebDriverWait wait;
	

	@Given("User is on the login page")
	public void userOnLoginPage() {
		driver = Driverfactory.initializeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		loginpage = new LoginPage(driver);
		loginpage.openloginpage();
	}

	@When("User enters valid credentials and clicks login button")
	public void userentersvalidcredentials() {
		loginpage.enterUsername("usha.amaravadi@gmail.com");
		loginpage.enterPassword("Numpi3737");
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
		String expectedurl = "/register";
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
		loginpage.enterUsername("usha.amaravadi@gmail.com");
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
		loginpage.clearusername();
		loginpage.enterPassword("Numpi3737");
		loginpage.clickLoginButton();
	}

	@When("User clicks login button after entering invalid username and valid password")
	public void userenteredinvalidusername() {
		loginpage.enterUsername("userx@graphic.com");
		loginpage.enterPassword("Numpi3737");
		loginpage.clickLoginButton();
	}

	@Then("User should see an error message {string}")
	public void userseeserrorinvalidusermsg(String expectedmsg) {
		String actualmsg = loginpage.getinvalidusererror();
		assertEquals(actualmsg, expectedmsg, "Message mismatch!");
	}

	@When("User clicks login button after entering valid username and invalid password")
	public void userenteredinvalidpassword() {
		loginpage.enterUsername("usha.amaravadi@gmail.com");
		loginpage.enterPassword("Varlet3737");
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
     //   loginpage.userclicksdropdown();
        loginpage.userclicksregisterlink();
    }

    @Then("User should see Register link")
    public void user_should_see_register_link() {
    	Assert.assertTrue(loginpage.isRegisterLinkVisible(),"Register link is not visible on login page!"
    	       );
    	}
    @When("User navigates to Signin Link on loginpage")
    public void user_navigates_signin_link() {
     //   loginpage.userclicksdropdown();
        loginpage.userclickssigninlink();
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

	    
	    

	

	























	
