package pageobject;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;

import java.time.Duration;
import java.util.List;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait;
	// @FindBy(xpath = "//a[normalize-space(text())='Register']")

	@FindBy(linkText = "Register")
	WebElement registerlink;
	@FindBy(xpath = "//div[@class = 'card-body d-flex flex-column']")
	// @FindBy(xpath = "//a[contains(@href, 'data-structures-introduction') and
	// contains(text(),'Get Started')]")
	// @FindBy(xpath = "//a[@href='data-structures-introduction' and text()='Get
	// Started']")
	List<WebElement> parent;

	@FindBy(xpath = "//a[text()='Data Structures']")
	private WebElement dataStructures;
	@FindBy(xpath = "//div[@class='dropdown-menu show']")
	private WebElement dropdownMenus;
	@FindBy(xpath = "//a[@class='dropdown-item']")
	private List<WebElement> dropdownItems;


//	private boolean itemFound;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void openHomePage() {
		driver.get("https://dsportalapp.herokuapp.com/home");

	}

	public void clickRegisterLink() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(registerlink));
		registerlink.click();
	}

	public void clickGettingStarted(String pythondatastructures) {
		// gettingStartedButton.click();
		System.out.println(parent.size());
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='card-body d-flex flex-column']"));
		for (int i = 0; i < elements.size(); i++) {
			WebElement el = driver.findElements(By.xpath("//div[@class='card-body d-flex flex-column']")).get(i);
			List<WebElement> children = el.findElements(By.xpath(".//h5"));
			for (WebElement child : children) {
				String text = child.getText();
				System.out.println(text);
				if (text.equals(pythondatastructures)) {
					el.findElement(By.xpath(".//a")).click(); // click inside this element
					return; // stop the test after clicking
				}
			}

		}
	}

	public void clickRegisterLink(String linkText) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(registerlink));
		registerlink.click();
		// TODO Auto-generated method stub

	}

	// public void clickRegisterLink(String linkText) {
	// TODO Auto-generated method stub

	// }

	/*
	 * public Boolean dropDownlist(String moduleDetails) {
	 * 
	 * wait.until(ExpectedConditions.visibilityOf(dataStructures));
	 * dataStructures.click();
	 * 
	 * //wait.until(ExpectedConditions.visibilityOfAllElements(dropdownItems));
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.
	 * xpath("//div[@class='dropdown-menu show']")));
	 * System.out.println(dropdownItems.size());
	 * 
	 * itemFound = false;
	 * 
	 * for (WebElement item : dropdownItems) {
	 * 
	 * System.out.print("line no  78 : " + item.getText()); if
	 * (item.getText().trim().equalsIgnoreCase(moduleDetails)) { item.click();
	 * String url = driver.getCurrentUrl(); System.out.println(url +
	 * ": In line 80");
	 * 
	 * String expectedUrlEndpoint = moduleDetails;
	 * 
	 * if (moduleDetails.contains("Linked List")) { expectedUrlEndpoint =
	 * "linked-list"; }
	 * if(url.toLowerCase().contains(expectedUrlEndpoint.toLowerCase())) {
	 * System.out.print("URL contains endpoint details "); itemFound = true; }else {
	 * itemFound = false; }
	 * 
	 * break; } }
	 * 
	 * return itemFound;
	 * 
	 * 
	 * }
	 */

}