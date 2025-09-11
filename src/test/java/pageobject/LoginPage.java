package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.net.UrlChecker.TimeoutException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ConfigReader;

import java.time.Duration;



public class LoginPage {

	WebDriver driver;
	@FindBy(id = "id_username")
	WebElement usernameinput;
	@FindBy(id = "id_password")
	WebElement passwordinput;
	@FindBy(xpath = "//input[@type='submit' and @value='Login']")
	WebElement loginbtn;
	@FindBy(xpath = "//h5[text()='Data Structures-Introduction']") 
    WebElement userseesintroductionpage;
	@FindBy(xpath = "//a[@href='/register']")
	WebElement registerlink;
	//@FindBy(xpath = "//a[@href='/login']")
	//WebElement signinlink;
	@FindBy(css = "ul a[href='/login']")
	WebElement signinlink;
	@FindBy(css = ".alert-primary")
	WebElement loginErrorMessage;
	@FindBy(xpath= "//a[contains(@class,'dropdown-toggle') and text()='Data Structures']")
	WebElement userclickingdropdown;
	@FindBy(xpath = "//div[contains(@class,'dropdown-menu show')]")
	WebElement userseesdropdownmenu;
	@FindBy(xpath = "//div[contains(@class, 'alert') and normalize-space(text())='You are logged in']")
	WebElement userlogssuccessfully;

	
	 public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);	
	    }
	// Actions
	 public void openloginpage() {
		 String baseUrl = ConfigReader.get("url");
		    String loginPath = ConfigReader.get("login.url");
		    driver.get(baseUrl + loginPath);
		
	    }

	    public void enterUsername(String username) {
	        usernameinput.clear();
	        usernameinput.sendKeys(username);
	    }

	    public void enterPassword(String password) {
	        passwordinput.clear();
	        passwordinput.sendKeys(password);
	    }

	   
	    public String gethomepage() {
	        return userseesintroductionpage.getText();
	    }
	    
	    public void clickRegisterLink() {
	    	registerlink.click();
	    }
	    public String getCurrentUrl() {
	        return driver.getCurrentUrl();
	    }
	    
		public void clearusername() {
			usernameinput.clear();
			
		}
	
		public void clearpassword() {
			passwordinput.clear();
			
		}
		public void clickLoginButton() {
			 
			        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			        wait.until(ExpectedConditions.elementToBeClickable(loginbtn));
			        loginbtn.click();
			    }
	        
	    

		public String gettextmsg() {
			
			    JavascriptExecutor js = (JavascriptExecutor) driver;
			    return (String) js.executeScript("return arguments[0].validationMessage;", usernameinput);
		}
		
		public String getvalidationpass() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
		    return (String) js.executeScript("return arguments[0].validationMessage;", passwordinput);
		}
		public String getinvalidusererror() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.visibilityOf(loginErrorMessage));
			
			return loginErrorMessage.getText();
		}
		public void userclicksdropdown() {
			
			userclickingdropdown.click();
		}
		public boolean isDropdownVisible() {
			return userseesdropdownmenu.isDisplayed();
		}
		public void selectModule(String module) {
			userclickingdropdown.click();

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement moduleLink = wait.until(ExpectedConditions.elementToBeClickable(
		        By.xpath("//a[normalize-space()='" + module + "']")));

		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", moduleLink);
		    try {
		        moduleLink.click();
		    } catch (ElementNotInteractableException e) {
		        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", moduleLink);
		    }
		}
		public WebElement getIntroductionHeadingElement() {
		    return userseesintroductionpage;
		}

		public WebElement getDropdownElement() {
		    return userclickingdropdown;
		}
		public void userclicksregisterlink() {
			new WebDriverWait(driver, Duration.ofSeconds(10))
	        .until(ExpectedConditions.elementToBeClickable(registerlink))
	        .click();
		}
		
		public boolean isRegisterLinkVisible() {
		    try {
		    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerlink);
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		       // WebElement registerLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
		          //  By.xpath("//a[contains(@href, '/register')]")
		       
		        //));
		        wait.until(ExpectedConditions.visibilityOf(registerlink));
		        return registerlink.isDisplayed();
		    } catch (TimeoutException e) {
		        System.out.println("Register link not visible in time.");
		        return false;
		    }
		}

		
		public void userclickssigninlink() {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			    try {
			        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(signinlink));
			        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			        element.click();
			    } catch (TimeoutException e) {
			        throw new TimeoutException("Signin link not clickable after waiting: " + e.getMessage());
			    }
			
			//new WebDriverWait(driver, Duration.ofSeconds(10))
	       // .until(ExpectedConditions.elementToBeClickable(signinlink))
	       // .click();
			
		}
		public String getloggingmsg() {
			
			return userlogssuccessfully.getText().trim();
		}
		public WebElement getSuccessMessageElement() {
			return userlogssuccessfully;
		}
		
		public boolean isSigninLinkVisible() {
		try {	
			
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signinlink);
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			//wait.until(ExpectedConditions.visibilityOf(registerlink));
	        //return registerlink.isDisplayed();
			new WebDriverWait(driver, Duration.ofSeconds(20))
	        .until(ExpectedConditions.visibilityOf(signinlink));
	    return signinlink.isDisplayed();
		}catch (TimeoutException e) {
	        System.out.println("Signin link not found or not visible.");
	        return false;
	    }
		
		
		
}
		

		
	

}
	

