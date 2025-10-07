
package stepdefinition;

import static org.testng.Assert.assertTrue;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.DatastructuresPages;
import pageobject.HomePage;
import pageobject.LoginPage;
import utilities.ConfigReader;
import utilities.Excelreader;

public class DatastructuresTest extends BaseSteps {
	private LoginPage loginpage;
	private WebDriverWait wait;
	private HomePage homepage;
	private DatastructuresPages datastructures;
	String oldOutput;
	String newOutput;
	private final String baseUrl = ConfigReader.get("url");
	private final String loginPath = ConfigReader.get("login.url");
	private final String homePath = ConfigReader.get("home.url");
	private final String username = ConfigReader.get("username");
	private final String password = ConfigReader.get("password");
	private final String dsIntroPath = ConfigReader.get("dsintro.url");
	private final String timeComplexityPath = ConfigReader.get("timecomplexity.url");
	private final String timeComplexityPathFull = ConfigReader.get("timecomplexity.url.full");
	private final String tryEditorPath = ConfigReader.get("tryeditor.url");
	String excelpath = "src/test/resources/data/testdata.xlsx";
	String sheetname = "Datascenario";

	public DatastructuresTest() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		datastructures = new DatastructuresPages(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	@Given("User is on the Home page after logging in")
	public void user_is_on_home_page_after_logging_in() {
		driver.manage().deleteAllCookies();
		driver.get(baseUrl + loginPath);
		loginpage.enterUsername(username);
		loginpage.enterPassword(password);
		loginpage.clickLoginButton();
		wait.until(ExpectedConditions.urlContains(homePath));
		Assert.assertTrue(driver.getCurrentUrl().contains(homePath));
	}

	@When("User clicks the Getting Started button in Data Structures - Introduction")
	public void user_clicks_the_button_in_data_structures_introduction() {
		// homepage.clickGettingStarted("Data Structures-Introduction");
		homepage.clickGettingStarted(datastructures.getDsIntroSectionName());
	}
	/*
	 * @Given("User is on the Data Structures page") public void
	 * user_is_on_the_datastructures_Page() { String expectedPath = ""; if
	 * (onpage.equalsIgnoreCase(datastructures.getDsHeading())) { expectedPath =
	 * dsIntroPath; //if
	 * (onpage.equalsIgnoreCase(datastructures.getDsIntroHeading())) { //if
	 * (onpage.equalsIgnoreCase("Data Structures")) { // datastructures.opendata();
	 * /data-structures-introduction
	 * 
	 * wait.until(ExpectedConditions.urlContains(expectedPath)); }else { throw new
	 * IllegalArgumentException("Page name not recognized: " + onpage); }
	 * System.out.println("Current URL: " + driver.getCurrentUrl());
	 * 
	 * }
	 */

	@Given("User is on the Data Structures page")
	public void user_is_on_the_datastructures_Page() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-display");
		String onpage = data.get("Page");
		String expectedPath = "";
		if (onpage.equalsIgnoreCase(datastructures.getDsHeading())) {
			expectedPath = dsIntroPath;
			// if (onpage.equalsIgnoreCase(datastructures.getDsIntroHeading())) {
			// if (onpage.equalsIgnoreCase("Data Structures")) {
			// datastructures.opendata(); /data-structures-introduction

			wait.until(ExpectedConditions.urlContains(expectedPath));
		} else {
			throw new IllegalArgumentException("Page name not recognized: " + onpage);
		}
		System.out.println("Current URL: " + driver.getCurrentUrl());

	}
	/*
	 * @Given("User is on the Data Structures page") public void
	 * user_is_on_the_datastructures_Page() { Map<String, String> data =
	 * Excelreader.getTestDataByAction(sheetname, "Datastructure-display"); String
	 * onpage = data.get("Page"); String expectedPath = "";
	 * 
	 * System.out.println("[DEBUG] onpage value from Excel: '" + onpage + "'");
	 * System.out.println("[DEBUG] Expected heading from page object: '" +
	 * datastructures.getDsHeading() + "'");
	 * 
	 * if (onpage.equalsIgnoreCase(datastructures.getDsHeading()) ||
	 * onpage.equalsIgnoreCase("Datastructures") ||
	 * onpage.equalsIgnoreCase("Data Structures")) {
	 * 
	 * expectedPath = dsIntroPath;
	 * wait.until(ExpectedConditions.urlContains(expectedPath)); } else { throw new
	 * IllegalArgumentException("Page name not recognized: " + onpage); }
	 * 
	 * System.out.println("Current URL: " + driver.getCurrentUrl()); }
	 */

	@When("User views content under DS page")
	public void user_views_datastructures_on_ds_page() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-display");
		Map<String, String> datatopics = Excelreader.getTestDataByAction(sheetname, "Datastructure-Topic");
		String heading = data.get("Expected heading");
		String headingtopics = datatopics.get("Expected heading");
		System.out.println("second scenario heading: " + heading);
		System.out.println("second scenario heading: " + headingtopics);

		// if (heading.equals("Data Structures-Introduction")) {
		if (heading.equals(datastructures.getDsIntroHeading())) {
			Assert.assertEquals(datastructures.getPageHeading(), heading);
		} // else if (heading.equals("Topics Covered")) {
		else if (headingtopics.equals(datastructures.getTopicsCoveredHeading())) {
			Assert.assertTrue(datastructures.isTopicsCoveredDisplayed(), " text is not displayed");
		} else {
			throw new IllegalArgumentException("Unexpected heading: " + heading);
		}
	}
	// Assert.assertEquals(datastructures.getPageHeading(), heading);

	@Then("User should see displayed content under Datastructures module")
	public void user_should_see_the_content_displayed_under_datastructures_module() {
		Assert.assertTrue(datastructures.isDataContentDisplayed(), "content is not visible");

	}

	@Given("User on Data Structures-Introduction page")
	public void user_is_on_the_datastructures_introduction_Page() {
		driver.get(baseUrl + loginPath);
		loginpage.fetchexceldata();
		wait.until(ExpectedConditions.urlContains(homePath));

		System.out.println("[DEBUG] After login. Current URL: " + driver.getCurrentUrl());
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-Time");
		String intropage = data.get("Page");
		// if (intropage.equalsIgnoreCase("Data Structures-Introduction")) {
		if (intropage.trim().equalsIgnoreCase(datastructures.getDsIntroHeading())) {
			// datastructures.opendata();
			driver.get(baseUrl + dsIntroPath);
			wait.until(ExpectedConditions.urlContains(dsIntroPath));
			System.out.println("[DEBUG] Navigated to DS Intro. Current URL: " + driver.getCurrentUrl());
		} else {
			throw new IllegalArgumentException("Page not found " + intropage);
		}
		System.out.println("Current URL: " + driver.getCurrentUrl());

	}

	@When("User clicks on Time Complexity link")
	public void user_clicks_on_timecomplexity_link() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-Time");
		String linkText = data.get("Expected heading");
		if (linkText.equalsIgnoreCase(datastructures.getTimeComplexityLinkText())) {

			// if (linktext.equalsIgnoreCase("Time Complexity")) {
			datastructures.clickTimeComplexityLink();
		} else {
			throw new IllegalArgumentException("Unexpected heading: " + linkText);
		}
	}

	@Then("User should see Time Complexity page")
	public void user_should_see_timecomplexity_page() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-Time");
		String linkText = data.get("Expected heading");
		String currentUrl = driver.getCurrentUrl();
		if (linkText.equalsIgnoreCase(datastructures.getTimeComplexityHeading())) {
			// if (seesexpectedpage.equalsIgnoreCase("Time Complexity")) {
			assertTrue(currentUrl.contains(timeComplexityPath)); // or exact match
		} else {
			throw new IllegalArgumentException("Unexpected page: " + linkText);
		}
		Assert.assertTrue(datastructures.isTimeComplexityHeadingDisplayed(), "Time complexity heading not visible");
	}
	/*
	 * @Given("User is on the {string} page for timecomplexity") public void
	 * user_is_on_the_tryeditor_page(String editorpage) { if
	 * (editorpage.equalsIgnoreCase(datastructures.getTryEditorName())) { //if
	 * (editorpage.equalsIgnoreCase("tryEditor")) { // datastructures.opendata();
	 * driver.get(baseUrl + tryEditorPath);
	 * wait.until(ExpectedConditions.urlContains(tryEditorPath)); }else { throw new
	 * IllegalArgumentException("tryeditor page not found " + editorpage); }
	 * 
	 * }
	 */

	@Given("User is on Time Complexity page for timecomplexity")
	public void user_should_see_content_in_timecomplexity_page() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-Time");
		String timecomplexpage = data.get("Expected heading");
		if (timecomplexpage.equalsIgnoreCase(datastructures.getTimeComplexityHeading())) {
			// if (timecomplexpage.equalsIgnoreCase("Time Complexity")) {
			// datastructures.opendata();
			driver.get(timeComplexityPathFull);
			// datastructures.navigateToTimeComplexityPage();
			System.out.println("[DEBUG] Navigated to 123: " + driver.getCurrentUrl());
			wait.until(ExpectedConditions.urlContains(timeComplexityPath));
		} else {
			throw new IllegalArgumentException("Page not found " + timecomplexpage);
		}
		System.out.println("Current URL: " + driver.getCurrentUrl());

	}

	@When("User navigates to view Time Complexity page")
	public void user_navigates_to_view_time_complexity_page() {
		// Assert.assertTrue(driver.getCurrentUrl().contains("/time-complexity/"),
		// "User is not on the expected Time Complexity page");
		Assert.assertTrue(driver.getCurrentUrl().contains(timeComplexityPath),
				"User is not on the expected Time Complexity page");
	}

	/*
	 * @Then("User should see content in {string} page") public void
	 * user_should_see_content_in_time_complexity_page(String page) {
	 * //System.out.println("[DEBUG] Current URL: " + driver.getCurrentUrl());
	 * 
	 * // boolean visible = datastructures.isTimeComplexityHeadingDisplayed();
	 * 
	 * // Assert.assertTrue(visible, "Time Complexity content not visible"); if
	 * (page.equalsIgnoreCase(datastructures.getTimeComplexityHeading())) { // if
	 * (page.equalsIgnoreCase("Time Complexity")) {
	 * 
	 * Assert.assertTrue(datastructures.isTimeComplexityHeadingDisplayed(),
	 * "Time Complexity content not visible");
	 * 
	 * } else { throw new
	 * IllegalArgumentException("Unexpected page for content verification: " +
	 * page); } }
	 */
	@Then("User should see content in Time Complexity page")
	public void user_should_see_content_in_time_complexity_page() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-Time");
		String heading = data.get("Expected heading");
		// System.out.println("[DEBUG] Current URL: " + driver.getCurrentUrl());

		// boolean visible = datastructures.isTimeComplexityHeadingDisplayed();

		// Assert.assertTrue(visible, "Time Complexity content not visible");
		if (heading.equalsIgnoreCase(datastructures.getTimeComplexityHeading())) {
			// if (page.equalsIgnoreCase("Time Complexity")) {

			Assert.assertTrue(datastructures.isTimeComplexityHeadingDisplayed(), "Time Complexity content not visible");

		} else {
			throw new IllegalArgumentException("Unexpected page for content verification: " + heading);
		}
	}

	@When("User clicks Try Here button")
	public void user_clicks_try_here_button() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-tryeditor");
		String buttonname = data.get("Button");
		if (buttonname.equalsIgnoreCase(datastructures.getTryHereButtonText())) {
			// if (buttonname.equalsIgnoreCase("Try here")) {
			datastructures.clickTryHereButton();
		} else {
			throw new IllegalArgumentException("Unexpected heading: " + buttonname);
		}
	}

	@Then("User should be redirected to a page having an try Editor with a Run button to test")
	public void user_should_see_try_editor_with_run_button() {
		wait.until(ExpectedConditions.urlContains(tryEditorPath));
		// wait.until(ExpectedConditions.urlContains("/tryEditor"));
		Assert.assertTrue(datastructures.isTryEditorVisible(), "Try Editor is not visible on the page.");
		Assert.assertTrue(datastructures.isRunButtonVisible(), "Run Button is not visible on the page.");
	}

	@Given("User is on the tryEditor page for timecomplexity for nocode")
	public void user_is_on_the_tryeditor_page() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-nocode");
		String editorpage = data.get("Expected heading");
		if (editorpage.equalsIgnoreCase(datastructures.getTryEditorName())) {
			// if (editorpage.equalsIgnoreCase("tryEditor")) {
			// datastructures.opendata();
			driver.get(baseUrl + tryEditorPath);
			wait.until(ExpectedConditions.urlContains(tryEditorPath));
		} else {
			throw new IllegalArgumentException("tryeditor page not found " + editorpage);
		}

	}

	@When("User clicks the Run without entering the code in the Editor")
	public void user_clicks_the_run_button_without_entering_the_code() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-nocode");
		String button = data.get("Button");

		System.out.println(" The Value of button123 is - " + button);

		if (!button.equals("Run")) {
			throw new IllegalArgumentException("Unexpected button 5656: " + button);
		}

		System.out.println("datastructures.getRunButtonText()5647 - " + datastructures.getRunButtonText());
		// if (button.equalsIgnoreCase(datastructures.getRunButtonText())) {
		if (button.equalsIgnoreCase(datastructures.getRunButtonText())) {
			// if (button.equalsIgnoreCase("Run")) {
			datastructures.clickRunButton();
			System.out.println("Inside if clause on try editor page5647");
		} else {

			throw new IllegalArgumentException("Unexpected button234: " + button);
		}
	}

	// @Then("User should be able to see an error message in alert window for
	// timecomplexity")
	// public void user_should_be_able_to_see_error_message_in_alert_window() {
	// Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	// String alertMessage = alert.getText();
	// System.out.println("Alert Message: " + alertMessage);
	// alert.accept();
	// }
	/*
	 * @When("User writes the invalid code in Editor and clicks the Run Button")
	 * public void
	 * user_writes_the_invalid_code_in_editor_and_clicks_run_button(String
	 * runbutton) { datastructures.enterInvalidPythonCode(); if
	 * (runbutton.equalsIgnoreCase(datastructures.getRunButtonText())) { //if
	 * (runbutton.equalsIgnoreCase("Run")) { datastructures.clickRunButton(); } else
	 * { throw new IllegalArgumentException("Unexpected button: " + runbutton); } }
	 */
	@Given("User is on the tryEditor page for timecomplexity for invalidcode")
	public void user_is_on_the_tryeditor_pagewithinvalidcode() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-invalidcode");
		String editorpage = data.get("Expected heading");
		if (editorpage.equalsIgnoreCase(datastructures.getTryEditorName())) {
			// if (editorpage.equalsIgnoreCase("tryEditor")) {
			// datastructures.opendata();
			driver.get(baseUrl + tryEditorPath);
			wait.until(ExpectedConditions.urlContains(tryEditorPath));
		} else {
			throw new IllegalArgumentException("tryeditor page not found " + editorpage);
		}

	}

	@When("User writes the invalid code in Editor and clicks the Run Button")
	public void user_writes_the_invalid_code_in_editor_and_clicks_run_button() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-invalidcode");
		String runbutton = data.get("Button");
		String fetchinvalidcode = data.get("Code-input");
		System.out.println("Invalid code is: " + fetchinvalidcode);
		datastructures.enterInvalidPythonCode(fetchinvalidcode);
		if (runbutton.equalsIgnoreCase(datastructures.getRunButtonText())) {
			// if (runbutton.equalsIgnoreCase("Run")) {
			datastructures.clickRunButton();
		} else {
			throw new IllegalArgumentException("Unexpected button: " + runbutton);
		}
	}

	@Then("User should be able to see an error message in alert window")
	public void user_should_be_able_to_see_error_message_in_alert_window() {
		// Map<String, String> data = Excelreader.getTestDataByAction(sheetname,
		// "Datastructure-nocode");
		// String expectedAlert = data.get("Expectederror");
		/*
		 * try { wait.until(ExpectedConditions.alertIsPresent()); String alertmessage =
		 * driver.switchTo().alert().getText(); System.out.println("alert message is:" +
		 * alertmessage);
		 * Assert.assertTrue(alertmessage.contains("SyntaxError: bad input on line 1"),
		 * "Expected error message not found. Actual: " + alertmessage);
		 * driver.switchTo().alert().accept(); } catch(Exception e) {
		 * e.printStackTrace(); Assert.fail("exception occured:" + e.getMessage()); }
		 */

		try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			String alertmessage = alert.getText();
			System.out.println("alert message is:" + alertmessage);
			Assert.assertTrue(alertmessage.contains("SyntaxError"), "Expected error message not found.");
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception occurred: " + e.getMessage());
		}
	}

	@Given("User is on the tryEditor page for timecomplexity for validcode")
	public void user_is_on_the_tryeditor_page_forvalidcode() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-validcode");
		String editorpage = data.get("Expected heading");
		if (editorpage.equalsIgnoreCase(datastructures.getTryEditorName())) {
			// if (editorpage.equalsIgnoreCase("tryEditor")) {
			// datastructures.opendata();
			driver.get(baseUrl + tryEditorPath);
			wait.until(ExpectedConditions.urlContains(tryEditorPath));
		} else {
			throw new IllegalArgumentException("tryeditor page not found " + editorpage);
		}

	}

	@When("User writes the valid code in Editor and clicks the Run Button")
	public void user_writes_the_valid_code_in_editor_and_clicks_the_run_button() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-validcode");
		// String runbuttons = data.get("Button");
		String fetchvalidcode = data.get("Code-input");
		datastructures.entervalidpythoncode(fetchvalidcode);
		// datastructures.entervalidpythoncode(datastructures.getDefaultValidCode());
		// datastructures.entervalidpythoncode("print('Hello DS Algo')");
		datastructures.clickRunButton();
		// datastructures.entervalidpythoncode();
		// if (runbutton.equalsIgnoreCase("Run")) {
		// datastructures.clickRunbutton();
		// } else {
		// throw new IllegalArgumentException("Unexpected button: " + runbutton);
		// }
	}

	@Then("User should be able to see output in the console")
	public void user_should_be_able_to_see_output_in_the_console() {

		// try {
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		// wait.until(ExpectedConditions.alertIsPresent());

		// Alert alert = driver.switchTo().alert();
		// System.out.println("Unexpected alert: " + alert.getText());
		// alert.accept();
		// Assert.fail("Unexpected alert present: " + alert.getText());
		// } catch (org.openqa.selenium.NoAlertPresentException e) {

		// } catch (org.openqa.selenium.TimeoutException e) {

		// }
		String outputmessage = datastructures.getOutputText();
		System.out.println("output message is" + outputmessage);
		Assert.assertFalse(outputmessage.isEmpty(), "Expected output in console, but got none.");
	}

	@When("User clicks on back arrow")
	public void user_clicks_on_back_arrow() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-navigatetopic");
		String code = data.get("Code-input");

		if (code != null && !code.isEmpty()) {
			datastructures.entervalidpythoncode(code);
		}
		driver.navigate().back();
		// wait.until(ExpectedConditions.urlContains("/data-structures-introduction"));
		// wait.until(ExpectedConditions.urlContains("/time-complexity/"));

		// System.out.println("current url is: " + driver.getCurrentUrl());
	}

	@Given("User is on the tryEditor page for timecomplexity for validcode for modifiedoutput")
	public void user_is_on_the_tryeditor_page_forvalidcode_modifiedoutput() {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-clearoutput");
		String editorpage = data.get("Expected heading");
		if (editorpage.equalsIgnoreCase(datastructures.getTryEditorName())) {
			// if (editorpage.equalsIgnoreCase("tryEditor")) {
			// datastructures.opendata();
			driver.get(baseUrl + tryEditorPath);
			wait.until(ExpectedConditions.urlContains(tryEditorPath));
		} else {
			throw new IllegalArgumentException("tryeditor page not found " + editorpage);
		}

	}

	@When("User Runs the code, modifies it, and runs it again")
	public void user_runs_the_code_modifies_it_and_runs_it_again() throws InterruptedException {
		Map<String, String> data = Excelreader.getTestDataByAction(sheetname, "Datastructure-clearoutput");
		// String runbuttona = data.get("Button");
		String fetchvalidcode = data.get("Code-input");
		String modifiedvalidcode = data.get("Modified-Code");

		System.out.println("valid code is before modification: " + fetchvalidcode);
		datastructures.entervalidpythoncode(fetchvalidcode);
		// datastructures.entervalidpythoncode(datastructures.getFirstValidCode());
		// datastructures.entervalidpythoncode("print('Hi')");
		datastructures.clickRunButton();
		Thread.sleep(2000);

		oldOutput = datastructures.getOutputText();
		System.out.println("Old Output: " + oldOutput);

		System.out.println("valid code is after modification: " + modifiedvalidcode);
		datastructures.entervalidpythoncode(modifiedvalidcode);
		// datastructures.entervalidpythoncode(datastructures.getModifiedValidCode());
		// datastructures.entervalidpythoncode("print('Hi NumpyNinja')");
		datastructures.clickRunButton();
		Thread.sleep(2000);

		newOutput = datastructures.getOutputText();
		System.out.println("New Output: " + newOutput);
	}

	@Then("User should see old output replaced with new output")
	public void user_should_see_output_replaced_with_new_output() {
		System.out.println("oldOutput is: " + oldOutput);
		System.out.println("newOutput is: " + newOutput);
		Assert.assertNotEquals(newOutput, oldOutput, "New output is the same as old output. It was not replaced.");

	}

	@When("User writes the valid code in Editor and clicks the back arrow")
	public void user_writes_valid_code_and_clicks_back_arrow() {

		datastructures.entervalidpythoncode(datastructures.getFinalBackArrowCode());
		// datastructures.entervalidpythoncode("print('Hello DS NumpyNinja')");
		driver.navigate().back();
		wait.until(ExpectedConditions.urlContains(dsIntroPath));
	}
	/*
	 * @When("User clicks on the {string} link on Data Structures Introduction page"
	 * ) public void user_clicks_on_practice_questions_link(String linkText) {
	 * 
	 * if (linkText.equalsIgnoreCase("Practice Questions")) {
	 * datastructures.clickPracticeQuestionslink(); } else { throw new
	 * IllegalArgumentException("Unexpected heading: " + linkText); } }
	 * 
	 * @Then("User should be directed to Practice Questions page") public void
	 * user_should_be_directed_to_practice_questions_page() {
	 * wait.until(ExpectedConditions.urlContains("/practice"));
	 * Assert.assertTrue(driver.getCurrentUrl().contains("/practice")); //
	 * wait.until(ExpectedConditions.urlContains("/practice")); //
	 * Assert.assertTrue(driver.getCurrentUrl().contains("/practice"),
	 * "Not on Practice Questions page.");
	 * 
	 * // Assert.assertTrue(datastructures.isPracticequestionsVisible(),
	 * "Practice Questions is not visible on the page.");
	 * 
	 * }
	 */
}
