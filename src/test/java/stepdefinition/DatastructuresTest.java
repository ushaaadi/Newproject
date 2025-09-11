  
package stepdefinition;
import static org.testng.Assert.assertTrue;
import java.time.Duration;

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
	
	@Given("User is on the {string} page")
	public void user_is_on_the_datastructures_Page(String onpage) {
		String expectedPath = "";
		if (onpage.equalsIgnoreCase(datastructures.getDsHeading())) {
			 expectedPath = dsIntroPath;
		//if (onpage.equalsIgnoreCase(datastructures.getDsIntroHeading())) {
		//if (onpage.equalsIgnoreCase("Data Structures")) {
           // datastructures.opendata(); /data-structures-introduction
			
			wait.until(ExpectedConditions.urlContains(expectedPath));	
        }else
        {
            throw new IllegalArgumentException("Page name not recognized: " + onpage);
        }
		System.out.println("Current URL: " + driver.getCurrentUrl());

	}
	@When("User views {string} on DS page")
	public void user_views_datastructures_on_ds_page(String heading) {
		//if (heading.equals("Data Structures-Introduction")) {
		if (heading.equals(datastructures.getDsIntroHeading())) {
	        Assert.assertEquals(datastructures.getPageHeading(), heading);
	    } //else if (heading.equals("Topics Covered")) {
		else if (heading.equals(datastructures.getTopicsCoveredHeading())) {
	        Assert.assertTrue(datastructures.isTopicsCoveredDisplayed(), " text is not displayed");
	    } else {
	        throw new IllegalArgumentException("Unexpected heading: " + heading);
	    }
	}
		//Assert.assertEquals(datastructures.getPageHeading(), heading);
	
	@Then("User should see displayed content under Datastructures module")
	public void user_should_see_the_content_displayed_under_datastructures_module() {
	    Assert.assertTrue(datastructures.isDataContentDisplayed(),"content is not visible");
	   
	}
	@Given("User on {string} page")
	public void user_is_on_the_datastructures_introduction_Page(String intropage) {
		 driver.get(baseUrl + loginPath);
	        loginpage.enterUsername(username);
	        loginpage.enterPassword(password);
	        loginpage.clickLoginButton();
	        wait.until(ExpectedConditions.urlContains(homePath));
	        
	        System.out.println("[DEBUG] After login. Current URL: " + driver.getCurrentUrl());

		//if (intropage.equalsIgnoreCase("Data Structures-Introduction")) {
	        if (intropage.trim().equalsIgnoreCase(datastructures.getDsIntroHeading())) {
           // datastructures.opendata(); 
			driver.get(baseUrl + dsIntroPath);
			wait.until(ExpectedConditions.urlContains(dsIntroPath));
			System.out.println("[DEBUG] Navigated to DS Intro. Current URL: " + driver.getCurrentUrl());
        }else
        {
            throw new IllegalArgumentException("Page not found " + intropage);
            }
		System.out.println("Current URL: " + driver.getCurrentUrl());

	}

	@When("User clicks on {string} link")
	public void user_clicks_on_timecomplexity_link(String linktext) {
		if (linktext.equalsIgnoreCase(datastructures.getTimeComplexityLinkText())) {
		//if (linktext.equalsIgnoreCase("Time Complexity")) {
	        datastructures.clickTimeComplexityLink();
	    } else {
	        throw new IllegalArgumentException("Unexpected heading: " + linktext);
	    }
	}
	@Then("User should see {string} page")
	public void user_should_see_timecomplexity_page(String seesexpectedpage) {
		    String currentUrl = driver.getCurrentUrl();
		    if (seesexpectedpage.equalsIgnoreCase(datastructures.getTimeComplexityHeading())) {
		   // if (seesexpectedpage.equalsIgnoreCase("Time Complexity")) {
		        assertTrue(currentUrl.contains(timeComplexityPath)); // or exact match
		    }else {
		        throw new IllegalArgumentException("Unexpected page: " + seesexpectedpage);

		}
		    Assert.assertTrue(datastructures.isTimeComplexityHeadingDisplayed(),"Time complexity heading not visible");

     
	   
	}
	@Given("User is on {string} page for timecomplexity")
	public void user_should_see_content_in_timecomplexity_page(String timecomplexpage) {
		if (timecomplexpage.equalsIgnoreCase(datastructures.getTimeComplexityHeading())) {
		//if (timecomplexpage.equalsIgnoreCase("Time Complexity")) {
           // datastructures.opendata(); 
			driver.get(timeComplexityPathFull);
			//datastructures.navigateToTimeComplexityPage();
	        System.out.println("[DEBUG] Navigated to: " + driver.getCurrentUrl());
            wait.until(ExpectedConditions.urlContains(timeComplexityPath));
        }else
        {
            throw new IllegalArgumentException("Page not found " + timecomplexpage);
            }
		System.out.println("Current URL: " + driver.getCurrentUrl());

	}
	@When("User navigates to view {string} page")
	public void user_navigates_to_view_time_complexity_page(String viewtimecomplexpage) {
		//Assert.assertTrue(driver.getCurrentUrl().contains("/time-complexity/"),
	          //  "User is not on the expected Time Complexity page");
		Assert.assertTrue(driver.getCurrentUrl().contains(timeComplexityPath),
	            "User is not on the expected Time Complexity page");
	}
	@Then("User should see content in {string} page")
	public void user_should_see_content_in_time_complexity_page(String page) {
		 //System.out.println("[DEBUG] Current URL: " + driver.getCurrentUrl());

		  //  boolean visible = datastructures.isTimeComplexityHeadingDisplayed();

		   // Assert.assertTrue(visible, "Time Complexity content not visible");
		if (page.equalsIgnoreCase(datastructures.getTimeComplexityHeading())) {
	   // if (page.equalsIgnoreCase("Time Complexity")) {
	    	
	        Assert.assertTrue(datastructures.isTimeComplexityHeadingDisplayed(), "Time Complexity content not visible");
	        
	    } else {
	       throw new IllegalArgumentException("Unexpected page for content verification: " + page);
	    }
	}
	@When("User clicks {string} button")
	public void user_clicks_try_here_button(String buttonname) {
		if (buttonname.equalsIgnoreCase(datastructures.getTryHereButtonText())) {
		//if (buttonname.equalsIgnoreCase("Try here")) {
	        datastructures.clickTryHereButton();
	    } else {
	        throw new IllegalArgumentException("Unexpected heading: " + buttonname);
	    }
	}
	@Then("User should be redirected to a page having an try Editor with a Run button to test")
	public void user_should_see_try_editor_with_run_button() {
		wait.until(ExpectedConditions.urlContains(tryEditorPath));
	    //wait.until(ExpectedConditions.urlContains("/tryEditor"));
	    Assert.assertTrue(datastructures.isTryEditorVisible(), "Try Editor is not visible on the page.");
	    Assert.assertTrue(datastructures.isRunButtonVisible(), "Run Button is not visible on the page.");
	}
	@Given("User is on the {string} page for timecomplexity")
	public void user_is_on_the_tryeditor_page(String editorpage) {
		if (editorpage.equalsIgnoreCase(datastructures.getTryEditorName())) {
		//if (editorpage.equalsIgnoreCase("tryEditor")) {
           // datastructures.opendata(); 
			driver.get(baseUrl + tryEditorPath);
			 wait.until(ExpectedConditions.urlContains(tryEditorPath));
        }else
        {
            throw new IllegalArgumentException("tryeditor page not found " + editorpage);
            }

	}
	@When("User clicks the {string} without entering the code in the Editor")
	public void user_clicks_the_run_button_without_entering_the_code(String button) {
		if (button.equalsIgnoreCase(datastructures.getRunButtonText())) {
		//if (button.equalsIgnoreCase("Run")) {
	        datastructures.clickRunButton();
	    } else {
	        throw new IllegalArgumentException("Unexpected button: " + button);
	    }
	}
	//@Then("User should be able to see an error message in alert window for timecomplexity")
	//public void user_should_be_able_to_see_error_message_in_alert_window() {
	//	Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	  //  String alertMessage = alert.getText();
	    //System.out.println("Alert Message: " + alertMessage);
	    //alert.accept();
	//}
	@When("User writes the invalid code in Editor and clicks the {string} Button")
	public void user_writes_the_invalid_code_in_editor_and_clicks_run_button(String runbutton) {
		datastructures.enterInvalidPythonCode();
		if (runbutton.equalsIgnoreCase(datastructures.getRunButtonText())) {
		//if (runbutton.equalsIgnoreCase("Run")) {
	        datastructures.clickRunButton();
	    }	 else {
	        throw new IllegalArgumentException("Unexpected button: " + runbutton);
	    }
	}
	@Then("User should be able to see an error message in alert window")
	public void user_should_be_able_to_see_error_message_in_alert_window() {
		/*try {
			wait.until(ExpectedConditions.alertIsPresent());
		String alertmessage = driver.switchTo().alert().getText();
		System.out.println("alert message is:" + alertmessage);
		Assert.assertTrue(alertmessage.contains("SyntaxError: bad input on line 1"), 
		        "Expected error message not found. Actual: " + alertmessage);
		driver.switchTo().alert().accept();
	} catch(Exception e) {
		e.printStackTrace();
		Assert.fail("exception occured:" + e.getMessage());
	}*/
		
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



	@When("User writes the valid code in Editor and clicks the {string} Button")
	public void user_writes_the_valid_code_in_editor_and_clicks_the_run_button(String runbutton) {
		datastructures.entervalidpythoncode(datastructures.getDefaultValidCode());
		//datastructures.entervalidpythoncode("print('Hello DS Algo')");
	    datastructures.clickRunButton();
		//datastructures.entervalidpythoncode();
		//if (runbutton.equalsIgnoreCase("Run")) {
	      //  datastructures.clickRunbutton();
	    //} else {
	      //  throw new IllegalArgumentException("Unexpected button: " + runbutton);
	    //}
	}
	@Then("User should be able to see output in the console")
	public void user_should_be_able_to_see_output_in_the_console() {
		
	
		//try {
	      //  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	        //wait.until(ExpectedConditions.alertIsPresent());

	        //Alert alert = driver.switchTo().alert();
	        //System.out.println("Unexpected alert: " + alert.getText());
	        //alert.accept(); 
	        //Assert.fail("Unexpected alert present: " + alert.getText());
	    //} catch (org.openqa.selenium.NoAlertPresentException e) {
	        
	    //} catch (org.openqa.selenium.TimeoutException e) {
	        
	    //}
		String outputmessage = datastructures.getOutputText();
		System.out.println("output message is" + outputmessage);
		Assert.assertFalse(outputmessage.isEmpty() ,"Expected output in console, but got none.");
}
	@When("User clicks on back arrow")
	public void user_clicks_on_back_arrow() {
		driver.navigate().back();
		//wait.until(ExpectedConditions.urlContains("/data-structures-introduction"));
	    //wait.until(ExpectedConditions.urlContains("/time-complexity/"));
	   
	   // System.out.println("current url is: " + driver.getCurrentUrl());
	}
	@When("User {string} the code, modifies it, and runs it again")
	public void user_runs_the_code_modifies_it_and_runs_it_again(String runbutton) {
		datastructures.entervalidpythoncode(datastructures.getFirstValidCode());
		//datastructures.entervalidpythoncode("print('Hi')");
	    datastructures.clickRunButton();
	    oldOutput = datastructures.getOutputText();
	    
	    datastructures.entervalidpythoncode(datastructures.getModifiedValidCode());
	   // datastructures.entervalidpythoncode("print('Hi NumpyNinja')");
	    datastructures.clickRunButton();
	    newOutput = datastructures.getOutputText();
	}
	@Then("User should see old output replaced with new output")
	public void user_should_see_output_replaced_with_new_output() {
	System.out.println("oldOutput is: " +oldOutput);	
	System.out.println("newOutput is: " +newOutput);
	Assert.assertNotEquals(newOutput, oldOutput, "New output is the same as old output. It was not replaced.");

}
	@When("User writes the valid code in Editor and clicks the back arrow")
	public void user_writes_valid_code_and_clicks_back_arrow() {
		
		datastructures.entervalidpythoncode(datastructures.getFinalBackArrowCode());
	   // datastructures.entervalidpythoncode("print('Hello DS NumpyNinja')");
	    driver.navigate().back();
	    wait.until(ExpectedConditions.urlContains(dsIntroPath));
	}
/*	@When("User clicks on the {string} link on Data Structures Introduction page")
	public void user_clicks_on_practice_questions_link(String linkText) {
	   
		if (linkText.equalsIgnoreCase("Practice Questions")) {
	        datastructures.clickPracticeQuestionslink();
	    } else {
	        throw new IllegalArgumentException("Unexpected heading: " + linkText);
	    }
	}
	@Then("User should be directed to Practice Questions page")
	public void user_should_be_directed_to_practice_questions_page() {
		wait.until(ExpectedConditions.urlContains("/practice"));
	    Assert.assertTrue(driver.getCurrentUrl().contains("/practice"));
	   // wait.until(ExpectedConditions.urlContains("/practice"));
	   // Assert.assertTrue(driver.getCurrentUrl().contains("/practice"), "Not on Practice Questions page.");

	   // Assert.assertTrue(datastructures.isPracticequestionsVisible(), "Practice Questions is not visible on the page.");
	    
	}*/
}
		
	
	
	
   
   
   
           
       

  