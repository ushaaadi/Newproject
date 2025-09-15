//Usha’s latest version as of 09 August 2025
package stepdefinition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;

import Base.Driverfactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.RegisterPage;
import utilities.ConfigReader;

public class RegisterTest {
	
	 WebDriver driver = Driverfactory.getDriver();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    HomePage homepage = new HomePage(driver);
	    RegisterPage registerpage = new RegisterPage(driver);
	    LoginPage loginpage = new LoginPage(driver);
	    
	    private final String baseUrl = ConfigReader.get("url");
	    private final String registerUrl = ConfigReader.get("register.url");
	    private final String loginUrl = ConfigReader.get("login.url");
	    private final String defaultPassword = ConfigReader.get("default.password");
	    private final String userdigits = ConfigReader.get("username.digits");
	   // String increment_username = "240";
	   // String username_digits = "65789582";
	    String baseUsername = "testuser";
	    String uniqueUsername = "testuser" + System.currentTimeMillis() + "@gmail.com";

@Given("User is on DS algo home page")
	public void userOnHomePage() {
		homepage.openHomePage();
		
	}
	
@When("User clicks on the {string} link in the Home Page")
//public void user_clicks_on_the_register_link_in_the_home_page() {
	//homepage.clickRegisterLink();

public void user_clicks_on_the_link_in_the_home_page(String linkText) {
homepage.clickRegisterLink(linkText);
}

@Then("User is directed to the registration page  where they can fill out the signup form")
public void user_is_directed_to_the_registration_page_where_they_can_fill_out_the_signup_form() {
	String expectedUrl = baseUrl + registerUrl;
 assertEquals(driver.getCurrentUrl(), expectedUrl,"incorrect registration page");
}

@Given("User on Registration page")
public void user_on_registration_page() {
	//String expectedUrl = baseUrl + registerUrl;
	//wait.until(ExpectedConditions.urlToBe(expectedUrl));
	//assertEquals(driver.getCurrentUrl(), expectedUrl, "Not on Registration page");
	registerpage.openregisterpage();
}
@When("User clicks Register button after entering Username with letters")
public void user_clicks_register_button_after_entering_username_with_letters() {
	System.out.println("Running letters username scenario");
	String username = "greekuser" + System.currentTimeMillis();
	registerpage.enterUsername(username);
	registerpage.enterPassword(defaultPassword);
	registerpage.enterPasswordconfirmation(defaultPassword);
	registerpage.clickRegisterButton();
}


@Then("User should see a success message {string} on the page")
public void user_should_see_a_success_message_in_home_page(String expectedmsg) {
    String actualMessage = registerpage.getAlertMessage();
    System.out.println("✅ Actual message: " + actualMessage);
    assertTrue(actualMessage.contains(expectedmsg),
            "Expected message to contain: '" + expectedmsg + "' but was: '" + actualMessage+ "'");
    assertTrue(registerpage.isRegistrationSuccessful(),
            "Registration was not successful — expected confirmation element not found.");
   // assertTrue(registerpage.isRegistrationSuccessful());---this is the valid line

    //assertTrue(actualMessage.contains(expectedmsg),
       // "Success message mismatch! Expected to contain: " + expectedmsg + " but was: " + actualMessage);
}
/*@Then("User should see a success message containing {string}")
public void user_should_see_a_success_message_containing(String expectedPartialMsg) {
    // Check the alert message content
    String actualMsg = registerpage.getAlertMessage();
    System.out.println("✅ Actual message: " + actualMsg);

    assertTrue(actualMsg.contains(expectedPartialMsg),
        "Expected message to contain: '" + expectedPartialMsg + "' but was: '" + actualMsg + "'");

    // Additional check: confirm registration success based on the next page element
    assertTrue(registerpage.isRegistrationSuccessful(),
        "Registration was not successful — expected confirmation element not found.");
}*/






@When("User clicks Register button after entering Username with digits")
public void user_clicks_register_button_after_entering_username_with_digits() {
	System.out.println("Running digits username scenario");
	registerpage.enterUsername(userdigits);
	registerpage.enterPassword(ConfigReader.get("digits.password"));
	registerpage.enterPasswordconfirmation(ConfigReader.get("digits.password"));
	registerpage.clickRegisterButton();
}
/*@When("User clicks Register button after entering Username with @\\/.\\/+\\/-\\/_")
public void user_clicks_register_button_after_entering_username_with() {
	System.out.println("Running digits username @/+_");
	 String username = "vindhyapu" + System.currentTimeMillis() + "@gmail.com";
	registerpage.enterUsername(username);
	registerpage.enterPassword(ConfigReader.get("specialchar.password"));
	registerpage.enterPasswordconfirmation(ConfigReader.get("specialchar.password"));
	registerpage.clickRegisterButton();
  
}trying another way*/
@When("The user enters the following details:")
public void the_user_enters_the_following_details(DataTable dataTable) {
    Map<String, String> data = dataTable.asMaps().get(0);
  //  String username = "vindhyapu" + System.currentTimeMillis() + "@gmail.com";
    System.out.println("Username: " + data.get("username"));
    System.out.println("Password: " + data.get("password"));
    System.out.println("Confirm Password: " + data.get("confirmPassword"));
    registerpage.enterUsername(data.get("username"));
   // registerpage.enterEmail(data.get("email"));
    registerpage.enterPassword(data.get("password"));
    registerpage.enterPasswordconfirmation(data.get("confirmPassword"));
}
@And("The user clicks the Register button")
public void the_user_clicking_the_register_button() {
    registerpage.clickRegisterButton();
}




/*@When("User clicks Register button after entering Username with @/./+/-/_")
public void user_clicks_register_button_after_entering_username_with() {
	//registerpage.registerWithSpecialCharUsername();
	System.out.println("Running digits username @/+_");
	 String username = "vindhyapu" + System.currentTimeMillis() + "@gmail.com";
	registerpage.enterUsername(username);
	registerpage.enterPassword(ConfigReader.get("specialchar.password"));
	registerpage.enterPasswordconfirmation(ConfigReader.get("specialchar.password"));
	registerpage.clickRegisterButton();
   
}*/

//@Then("User should see error message")
//public void user_should_see_error_message() {
    // Write code here that turns the phrase above into concrete actions
  //  throw new io.cucumber.java.PendingException();
//}

@When("User enters Username with space, valid password, password confirmation and clicks Register")
public void user_enters_username_with_space_valid_password_password_confirmation_and_clicks_register() {
	
	registerpage.enterUsername("user name with space186");
	registerpage.enterPassword(ConfigReader.get("default.userspace"));
	registerpage.enterPasswordconfirmation(ConfigReader.get("default.userspace"));
	registerpage.clickRegisterButton();
   
}

@Then("User should see error message {string}")
public void user_should_see_error_message(String expectedmsg) {
    String actualmsg = registerpage.getAlertMessage(); // Unified alert message
    System.out.println("Actual error message: " + actualmsg);
   // assertTrue(actualmsg.contains(expectedmsg), "Expected error message not found!");
    assertEquals(actualmsg, expectedmsg, "Error message mismatch!");
}



@When("User clicks Register with all fields empty")
public void user_clicks_register_with_all_fields_empty() {
    registerpage.clickRegisterButton();
}
@Then("User should remain on the registration page")
public void user_should_remain_on_the_registration_page() {
	 String currentUrl = driver.getCurrentUrl();
	 String expectedUrl = baseUrl + registerUrl;
	assertEquals(currentUrl, expectedUrl, "User was redirected unexpectedly");
}

@After
public void signOutAfterScenario() {
    try {
        // Check if sign-out link is present
        WebElement signOutLink = driver.findElement(By.xpath("//a[text()='Sign out']"));
        if (signOutLink.isDisplayed()) {
            signOutLink.click();
            System.out.println("Signed out successfully after scenario.");
        }
    } catch (Exception e) {
        System.out.println("Sign out link not found or already signed out.");
    }
}



@When("User enters valid Username with empty Password,empty Password Confirmation fields and clicks on Register button")
public void user_enters_valid_username_leaves_password_and_password_confirmation_empty_then_clicks_register() {
	registerpage.enterUsername("user" + System.currentTimeMillis() + "@gmail.com");
	registerpage.enterPassword("");
	registerpage.enterPasswordconfirmation("");
	registerpage.clickRegisterButton();
}

@When("User enters empty Username with all valid fields and clicks on Register button")
public void user_enters_empty_username_with_all_valid_fields_and_clicks_on_register_button() {
	registerpage.enterUsername("");
	registerpage.enterPassword(defaultPassword);
	registerpage.enterPasswordconfirmation(defaultPassword);
	registerpage.clickRegisterButton();
}


@When("User enters valid Username, valid Password, empty Password Confirmation fields and clicks on Register button")
public void user_enters_valid_username_and_password_leaves_confirmation_empty_then_clicks_register() {
	registerpage.enterUsername("user" + System.currentTimeMillis() + "@gmail.com");
    registerpage.enterPassword("Valid@123");
    registerpage.enterPasswordconfirmation("");
    registerpage.clickRegisterButton();
}


@When("User enters existing Username, Password, Password Confirmation fields and clicks on Register button")
public void user_enters_existing_username_password_password_confirmation_and_clicks_register() {
	String existingUser = ConfigReader.get("username");
    String password24 = ConfigReader.get("password");
	registerpage.enterUsername(existingUser);
	registerpage.enterPassword(password24);
	registerpage.enterPasswordconfirmation(password24);
	registerpage.clickRegisterButton();

}


@When("User enters Username, sets Password, Passwordconfirmation as similar to user information and clicks on Register button")
public void user_enters_username_sets_password_passwordconfirmation_as_similar_to_user_information_and_clicks_on_register_button() {
	registerpage.enterUsername(ConfigReader.get("user.similar.info.username"));
	registerpage.enterPassword(ConfigReader.get("user.similar.info.password"));
	registerpage.enterPasswordconfirmation(ConfigReader.get("user.similar.info.password"));
	registerpage.clickRegisterButton();

}

@When("User enters valid username,Password ,Password Confirmation of exact {int} characters and clicks on Register button")
public void user_enters_valid_username_password_password_confirmation_of_characters_and_clicks_on_register_button(Integer length) {
	String username = "user" + System.currentTimeMillis() + "@gmail.com";
	 String password = "pea2d@32";  
	 
	registerpage.enterUsername(username);
	registerpage.enterPassword(password);
	registerpage.enterPasswordconfirmation(password);
	registerpage.clickRegisterButton();
}


@Then("User should be able to register")
public void user_should_be_able_to_register() {
	 assertTrue(registerpage.isRegistrationSuccessful(),"User was not successfully registered");
}
@When("User enters valid username,Password ,Password Confirmation of 7 characters and clicks on Register button")
public void user_enters_valid_username_password_password_confirmation_of_7_characters_and_clicks_on_register_button() {
    String username = "user" + System.currentTimeMillis() + "@gmail.com";
    String password = ConfigReader.get("password.7.chars");
    
    registerpage.enterUsername(username);
    registerpage.enterPassword(password);
    registerpage.enterPasswordconfirmation(password);
    registerpage.clickRegisterButton();
}
@When("User enters valid username,Password, Password Confirmation of 6 characters,whitespaces and clicks on Register button")
public void user_enters_valid_username_character_password_with_whitespaces_and_clicks_register() {
	String username = "user" + System.currentTimeMillis() + "@gmail.com";
	String password = ConfigReader.get("password.6.chars.whitespace");
	 
	registerpage.enterUsername(username);
	registerpage.enterPassword(password);
	registerpage.enterPasswordconfirmation(password);
	registerpage.clickRegisterButton();
}
@When("User enters valid username,password, confirmation password  and clicks on register button")
public void user_enters_valid_username_password_confirmation_password_and_clicks_register() {
	String username = "user" + System.currentTimeMillis() + "@gmail.com";
	String password = ConfigReader.get("password.simple");
	registerpage.enterUsername(username);
	registerpage.enterPassword(password);
	registerpage.enterPasswordconfirmation(password);
	registerpage.clickRegisterButton();
}
@When("User enters valid username, password with only numbers, confirms the same password and clicks on register button")
public void user_enters_valid_username_password_only_numbers_confirms_same_password_and_clicks_register() {
	String username = "user" + System.currentTimeMillis() + "@gmail.com";
	String password = ConfigReader.get("password.only.numbers");
	registerpage.enterUsername(username);
	registerpage.enterPassword(password);
	registerpage.enterPasswordconfirmation(password);
	registerpage.clickRegisterButton();
}
@When("User clicks Register button after entering different passwords in Password and Password Confirmation fields")
public void user_enters_different_passwords_password_password_confirmation_clicks_register() {
	String username = "user" + System.currentTimeMillis() + "@gmail.com";
	//String password = "12345678"; 
	registerpage.enterUsername(username);
	registerpage.enterPassword(ConfigReader.get("password.valid"));
	registerpage.enterPasswordconfirmation(ConfigReader.get("password.invalid"));
	registerpage.clickRegisterButton();
}
@When("User clicks on Login link under Register button")
public void user_clicks_on_login_link_under_register_button() {
	registerpage.clickloginlink();

}
@Then("System redirects the user to the Login page")
public void system_redirects_the_user_to_login_page() {
	String expectedUrl = baseUrl + loginUrl;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.urlToBe(expectedUrl));
	 String actualUrl = driver.getCurrentUrl();
	    assertEquals(actualUrl, expectedUrl, "User was not redirected to the Login page");
	    loginpage = new LoginPage(driver);
	
}
@When("User navigates to NumpyNinja on registrationpage")
public void user_navigates_to_numpyninja_on_registration_page() {
	registerpage.ScrolltoNumpyNinja();

}
@Then("User should see NumpyNinja title")
public void user_should_see_numpyninja_title() {
	assertTrue(registerpage.isNumpyNinjaVisible(), "NumpyNinja title is not visible on the page");
	
}
@When("User clicks on NumpyNinja link on registrationpage")
public void user_clicks_on_numpyninja_on_registration_page() {
	registerpage.clickNumpyNinja();

}
@Then("User redirects to DSalgo portal")
public void user_redirects_to_dsalgo() {
	wait.until(ExpectedConditions.visibilityOf(registerpage.getstartedbutton()));
	assertTrue(registerpage.isGetStartedVisible(), "NumpyNinja title is not visible on the page");
	
}
@When("User navigates to Register link on register page")
public void user_navigates_to_register_link_on_register_page() {
	
	registerpage.scrollToAndVerifyRegisterLink();
}
@Then("User should see Register link on register page")
public void user_should_be_able_to_see_register_link() {																			
	assertTrue(registerpage.isregisterlinkVisible(), "Register link is not visible on the page");
	
}
@When("User clicks on Registerlink on registerpage")
public void user_clicks_on_Registerlink_on_register() {
	registerpage.clickRRegisterlink();
	
		//wait.until(ExpectedConditions.elementToBeClickable(registerpage.clickregisterlink()));
		
	}


@Then("User redirects to Registration page")
public void user_redirects_to_registration_page() {
    String expectedUrl = baseUrl + registerUrl;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.urlToBe(expectedUrl));
    String actualUrl = driver.getCurrentUrl();
    assertEquals(actualUrl, expectedUrl, "User was not redirected to the Registration page");
    
	
}
@When("User navigates to Signin Link on loginpage from registerpage")
public void user_navigates_to_signin_link_on_login_page_from_register_page() {
 //   loginpage.userclicksdropdown();
	registerpage.scrollToSigninLink(); 
}
@Then("User should see Signin Link on register page")
public void user_should_see_signin_link_on_register_page() {
	
	assertTrue(registerpage.isSigninlinkVisible(), "Signin link is not visible on the page");

}

}



