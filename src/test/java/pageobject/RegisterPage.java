package pageobject;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ConfigReader;

public class RegisterPage {
	WebDriver driver;
	WebDriverWait wait;
	String registerUrl = ConfigReader.get("register.url.full");

	@FindBy(id = "id_username")
	WebElement usernameinput;
	@FindBy(id = "id_password1")
	WebElement passwordinputfield;
	@FindBy(id = "id_password2")
	WebElement passwordinputconfirmfield;
	@FindBy(xpath = "//input[@type='submit' and @value='Register']")
	WebElement registerbtn;
	@FindBy(css = "div.alert.alert-primary[role='alert']")
	WebElement alertMessage; // This will serve both success and error messages

	// @FindBy(css = "div.alert.alert-primary[role='alert']")
	// WebElement successMessage;
	// @FindBy(xpath = "//div[@class='alert alert-primary']")
	// WebElement errorMessage;
	// @FindBy(xpath = "//div[contains(@class, 'alert') and contains(text(),
	// 'password_mismatch')]")
	// WebElement errorMessage;

	@FindBy(xpath = "//div[@class='alert alert-primary' and normalize-space(text())='password_mismatch:The two password fields didn’t match.']")
	WebElement passworderrorMessage;

	// @FindBy(xpath = "//div[@class='alert alert-primary' and @role='alert']")
	// WebElement errorMessage;
	@FindBy(xpath = "//h5[contains(text(),'Data Structures-Introduction')]")
	WebElement registersuccess;

	@FindBy(xpath = "//a[@href='/login']")
	WebElement loginlink;

	@FindBy(xpath = "//a[contains(text(),'NumpyNinja')]")
	WebElement logoverification;

	@FindBy(xpath = "//a[@href='/home']")
	WebElement homegetstarted;
	// @FindBy(linkText = "Register")
	@FindBy(xpath = "//a[@href='/register']")
	WebElement clickregisterlink;
	// public String getErrorMessage() {
	// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	// wait.until(ExpectedConditions.visibilityOf(passworderrorMessage));
	// return passworderrorMessage.getText().trim();
	// }

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		// this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void openregisterpage() {
		driver.get(registerUrl);

	}

	public void enterUsername(String username) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOf(usernameinput));
		usernameinput.clear();
		usernameinput.sendKeys(username);

	}

	public void enterPassword(String password) {
		System.out.println("Entering password: '" + password + "'");
		wait.until(ExpectedConditions.visibilityOf(passwordinputfield));
		passwordinputfield.clear();
		passwordinputfield.sendKeys(password);

	}

	public void enterPasswordconfirmation(String passwordconfirmfield) {
		System.out.println("Entering confirm password: '" + passwordinputconfirmfield + "'");
		wait.until(ExpectedConditions.visibilityOf(passwordinputconfirmfield));
		passwordinputconfirmfield.clear();
		passwordinputconfirmfield.sendKeys(passwordconfirmfield);

	}

	public void clickRegisterButton() {
		System.out.println("Values in fields before register: ");
		System.out.println("Password: " + passwordinputfield.getAttribute("value"));
		System.out.println("Confirm: " + passwordinputconfirmfield.getAttribute("value"));
		wait.until(ExpectedConditions.elementToBeClickable(registerbtn));
		registerbtn.click();

	}

	// public String gettextmsg() {
	// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	// wait.until(ExpectedConditions.visibilityOf(successMessage));
	// return successMessage.getText().trim();
	// }

	public boolean isRegistrationSuccessful() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(registersuccess));
			return registersuccess.isDisplayed();

		} catch (Exception e) {

			return false;
		}

	}

	public String getPasswordErrorMessageElement() {
		return passworderrorMessage.getText();
	}

	public String getAlertMessage() {

		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		return alertMessage.getText().trim();
	}

	public void clickloginlink() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(loginlink))
				.click();

	}

	public void ScrolltoNumpyNinja() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(logoverification));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logoverification);
		} catch (Exception e) {
			System.out.println("Error scrolling to NumpyNinja: " + e.getMessage());
			throw e;
		}
	}

	public boolean isNumpyNinjaVisible() {
		try {
			wait.until(ExpectedConditions.visibilityOf(logoverification));
			return logoverification.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickNumpyNinja() {
		// TODO Auto-generated method stub
		logoverification.click();

	}

	public WebElement getstartedbutton() {
		wait.until(ExpectedConditions.elementToBeClickable(homegetstarted));

		return homegetstarted;
	}

	public boolean isGetStartedVisible() {
		return homegetstarted.isDisplayed();

	}

	public boolean isregisterlinkVisible() {
		return clickregisterlink.isDisplayed();

	}

	public void scrollToAndVerifyRegisterLink() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickregisterlink);
		wait.until(ExpectedConditions.visibilityOf(clickregisterlink));

	}

	// public void clickRegisterlink(String linkText) {
	// wait.until(ExpectedConditions.visibilityOf(clickregisterlink));
	// wait.until(ExpectedConditions.elementToBeClickable(clickregisterlink)).click();
	// clickregisterlink.click();
	// }

	public void scrollToSigninLink() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(loginlink));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginlink);
		} catch (Exception e) {
			System.out.println("Error scrolling to signin: " + e.getMessage());
			throw e; // rethrow for test to fail with proper reason
		}

	}

	public boolean isSigninlinkVisible() {
		return loginlink.isDisplayed();
	}

	public void clickRRegisterlink() {
		wait.until(ExpectedConditions.visibilityOf(clickregisterlink));
		wait.until(ExpectedConditions.elementToBeClickable(clickregisterlink)).click();// TODO Auto-generated method
																						// stub

	}

}