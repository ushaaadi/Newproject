
package stepdefinition;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Map;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.LoginPage;
import utilities.ConfigReader;
import utilities.Excelreader;
import utilities.LoggerLoad;

public class LoginTests extends BaseSteps {
	LoginPage loginpage;
	WebDriverWait wait;
	String excelpath = "src/test/resources/data/testdata.xlsx";
	String sheetname = "loginscenario";

	@Given("User is on the login page")
	public void userOnLoginPage() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		loginpage = new LoginPage(driver);
		loginpage.openloginpage();
		System.out.println("user is on the loging page");
		LoggerLoad.info("user is on the given page");

	}

	@When("User enters valid credentials and clicks login button")
	public void userentersvalidcredentials() {
		// Map<String, String> data = Excelreader.getTestDataByAction(sheetname,
		// "login-valid");
		// Map<String, String> data =
		// Excelreader.getTestDataByAction(excelpath,sheetname,"login-valid");
		// loginpage.enterUsername(data.get("username"));
		// loginpage.enterPassword(data.get("password"));
		// System.out.println(data);
		// System.out.println("Username is==" +data.get("username"));
		// loginpage.clickLoginButton();
		// loginpage.fetchdatafromexcel(sheetname,"login-valid");
		loginpage.fetchexceldata();

	}

	@Then("User should see the {string} heading")
	public void usershouldseeintropage(String expectedHeading) {
		wait.until(ExpectedConditions.visibilityOf(loginpage.getIntroductionHeadingElement()));
		String actualHeading = loginpage.gethomepage();
		System.out.println(actualHeading);
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

	@Then("User should see error message below Username textbox")
	public void userseeserrormsg() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "login-error");

		String actualmsg = loginpage.gettextmsg();
		System.out.println("actualmsg - " + actualmsg);
		String expectedmsg = data.get("expectederrormessage");
		System.out.println("expectedmsg - " + expectedmsg);
		assertEquals(actualmsg, expectedmsg, "Username message mismatch!");
	}

	// @When("User clicks login button after entering the {string} and leaves
	// {string} textbox empty")
	// public void usersenteredusername(String textbox1, String emptytextbox2) {
	// Map<String, String> data = Excelreader.getTestDataByAction(sheetname,
	// "login_empty_pass");
	// loginpage.enterUsername(data.get("username"));
	// loginpage.clearpassword();
	// loginpage.clickLoginButton();
	// loginpage.fetchexceldata();
	// }
	@When("User clicks login button after entering the Username and leaves Password textbox empty")
	public void usersenteredusername() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Login-passerror");
		loginpage.enterUsername(data.get("username"));
		loginpage.clearpassword(); // explicitly ensure password is empty
		loginpage.clickLoginButton();
	}
	// loginpage.fetchexceldata();

	@Then("User should see error message below Password textbox")
	public void userseeserrorpasswordmsg() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Login-passerror");
		String actualmsg = loginpage.getvalidationpass();
		String expectedmsg = data.get("expectederrormessage");
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
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "login-invaliduser");
		loginpage.enterUsername(data.get("username"));
		loginpage.enterPassword(data.get("password"));
		// loginpage.enterUsername("userx@graphic.com");
		// loginpage.enterPassword(ConfigReader.get("password"));
		loginpage.clickLoginButton();
	}

	@Then("User should see an error message")
	public void userseeserrorinvalidusermsg() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "login-invaliduser");
		String actualmsg = loginpage.getinvalidusererror();
		String expectedmsg = data.get("expectederrormessage");
		assertEquals(actualmsg, expectedmsg, "Message mismatch!");
	}

	@When("User clicks login button after entering valid username and invalid password")
	public void userenteredinvalidpassword() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "login_invalid_pass");
		loginpage.enterUsername(data.get("username"));
		loginpage.enterPassword(data.get("password"));
		// loginpage.enterUsername(ConfigReader.get("username"));
		// loginpage.enterPassword("Varlet3737");
		loginpage.clickLoginButton();
	}

	@When("User clicks on dropdown in login page")
	public void userseesdropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(loginpage.getDropdownElement()));
		loginpage.userclicksdropdown();
	}

	@Then("Datastructures dropdown is visible")
	public void dropdownvisible() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "dropdown visible");
		String expectedmsg = data.get("expectedheading");
		boolean isVisible = loginpage.isDropdownVisible();
		assertEquals(String.valueOf(isVisible).toUpperCase(), expectedmsg.toUpperCase(), "Dropdown is not visible!");
		// Assert.assertTrue(loginpage.isDropdownVisible(), expectedmsg,"Dropdown is not
		// visible!");
	}

	@When("User navigates to Register link on loginpage")
	public void user_navigates_register_link() {
		// loginpage.userclicksdropdown();
		// loginpage.userclicksregisterlink();
	}

	@Then("User should see Register link")
	public void user_should_see_register_link() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Registerlinkvisible");
		String expectedmsg = data.get("expectedheading");
		boolean isVisible = loginpage.isRegisterLinkVisible();
		System.out.println("isVisible 345671 - ");
		System.out.println("isVisible 345672 - " + String.valueOf(isVisible));
		assertEquals(String.valueOf(isVisible), expectedmsg.toLowerCase(),
				"Register link is not visible on loginpage!");
		// Assert.assertTrue(loginpage.isRegisterLinkVisible(),"Register link is not
		// visible on login page!"

	}

	@Given("User is on the home page")
	public void user_on_home_page() {
		driver.get(ConfigReader.get("home.url"));
		loginpage = new LoginPage(driver);
	}

	@When("User navigates to Signin Link")
	public void user_navigates_signin_link() {
		// loginpage.userclicksdropdown();
		// loginpage.userclickssigninlink();

		Assert.assertTrue(loginpage.isSigninLinkVisible(), "Signin link is not visible on login page!");
		
	}

	@Then("User should see Signin Link")
	public void user_should_see_signin_link() {
		Assert.assertTrue(loginpage.isSigninLinkVisible(), "Signin link is not visible on login page!");
	}

	@Then("User should be Logged in successfully")
	public void user_should_be_logged_in_successfully() {
		Map<String, String> data = Excelreader.getTestDataByAction("loginscenario", "Loggedin");
		wait.until(ExpectedConditions.visibilityOf(loginpage.getSuccessMessageElement()));
		String actualmsg = loginpage.getloggingmsg();
		System.out.println("actualmsg 345671 - " + actualmsg);
		String expectedmsg = data.get("expectedheading");
		Assert.assertEquals(actualmsg, expectedmsg, "Login success message mismatch!");
	}

}