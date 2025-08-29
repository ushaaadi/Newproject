
	package pageobject;

	import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class DatastructuresPages {
	    WebDriver driver;
	    WebDriverWait wait;

	    @FindBy(tagName = "h4")
	    WebElement fetchingheading;

	    @FindBy(xpath = "//strong[contains(text(), 'Purpose of Data structure')]")
	    WebElement displaydatamsg;

	    @FindBy(xpath = "//p[contains(text(),'Topics Covered')]")
	    WebElement fetchtopicscovered;

	    @FindBy(linkText = "Time Complexity")
	    WebElement timecomplexitylink;

	    @FindBy(xpath = "//p[contains(text(),'Time Complexity')]")
	    WebElement timecomplexityheading;

	    @FindBy(xpath = "//a[contains(text(),'Try here')]")
	    WebElement tryHerebutton;

	    @FindBy(xpath = "//div[contains(@class,'CodeMirror cm-s-default')]")
	    WebElement editor;

	    @FindBy(xpath = "//button[text()='Run']")
	    WebElement runButton;

	    @FindBy(id = "output")
	    WebElement consoleoutput;

	   /* @FindBy(linkText = "Practice Questions")
	    WebElement practicequestions;*/
	    @FindBy(css = "a.nav-link.dropdown-toggle")
	    WebElement dropdownButton;


	  /*  @FindBy(id = "dropdownMenuButton") 
	    WebElement dropdownButton;*/

	   
	   // @FindBy(css = "div.dropdown-menu.show a.dropdown-item")
	   // List<WebElement> dropdownItems;

	    public DatastructuresPages(WebDriver driver) {
	        this.driver = driver;
	       // this.driver = Driverfactory.initializeDriver(); 
	       
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        PageFactory.initElements(driver, this);
	    }

	    public void openDataPage() {
	        driver.get("https://dsportalapp.herokuapp.com/data-structures-introduction/");
	    }

	    public String getPageHeading() {
	        wait.until(ExpectedConditions.visibilityOf(fetchingheading));
	        return fetchingheading.getText().trim();
	    }

	    public boolean isDataContentDisplayed() {
	        try {
	            wait.until(ExpectedConditions.urlContains("/data-structures-introduction"));
	            wait.until(ExpectedConditions.visibilityOf(displaydatamsg));
	            return displaydatamsg.isDisplayed();
	        } catch (Exception e) {
	            System.out.println("ERROR: Element 'Purpose of Data structure' not visible: " + e.getMessage());
	            return false;
	        }
	    }

	    public boolean isTopicsCoveredDisplayed() {
	        try {
	            wait.until(ExpectedConditions.visibilityOf(fetchtopicscovered));
	            return fetchtopicscovered.isDisplayed();
	        } catch (Exception e) {
	            System.out.println("ERROR: Topics Covered element not visible: " + e.getMessage());
	            return false;
	        }
	    }

	    public void clickTimeComplexityLink() {
	        wait.until(ExpectedConditions.elementToBeClickable(timecomplexitylink)).click();
	    }

	    public boolean isTimeComplexityHeadingDisplayed() {
	        try {
	            wait.until(ExpectedConditions.visibilityOf(timecomplexityheading));
	            return timecomplexityheading.isDisplayed();
	        } catch (Exception e) {
	            System.out.println("ERROR: Time Complexity heading not visible: " + e.getMessage());
	            return false;
	        }
	    }

	    public void clickTryHereButton() {
	        wait.until(ExpectedConditions.elementToBeClickable(tryHerebutton)).click();
	    }

	    public boolean isTryEditorVisible() {
	        try {
	            return wait.until(ExpectedConditions.visibilityOf(editor)).isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public boolean isRunButtonVisible() {
	        try {
	            return wait.until(ExpectedConditions.visibilityOf(runButton)).isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public void clickRunButton() {
	    	 try {
	    	        // Ensure the button is both visible and clickable
	    	        wait.until(ExpectedConditions.refreshed(
	    	            ExpectedConditions.elementToBeClickable(runButton)
	    	        )).click();
	    	    } catch (Exception e) {
	    	        System.out.println("Run button could not be clicked: " + e.getMessage());
	    	        throw e; // rethrow so test fails if necessary
	    	    }
	    	}
	        //wait.until(ExpectedConditions.elementToBeClickable(runButton)).click();
	    

	    public void enterInvalidPythonCode() {
	        String invalidCode = "print\"hello";  // Invalid Python syntax
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("CodeMirror")));
	        ((JavascriptExecutor) driver).executeScript(
	            "document.querySelector('.CodeMirror').CodeMirror.setValue(arguments[0]);",
	            invalidCode
	        );
	    }

	 /*   public void enterValidPythonCode(String code) {
	        ((JavascriptExecutor) driver).executeScript(
	            "document.querySelector('.CodeMirror').CodeMirror.setValue(arguments[0]);",
	            code
	        );
	    }*/

	    public String getOutputText() {
	        try {
	            wait.until(ExpectedConditions.visibilityOf(consoleoutput));
	            return consoleoutput.getText().trim();
	        } catch (Exception e) {
	            System.out.println("ERROR: Output console not visible: " + e.getMessage());
	            return "";
	        }
	    }

	 /*   public void clickPracticeQuestionsLink() {
	        wait.until(ExpectedConditions.elementToBeClickable(practicequestions)).click();
	    }*/
	

	public void entervalidpythoncode(String code) {
		//codeEditor.clear();
        //codeEditor.sendKeys(code);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("CodeMirror")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript(
	        "let editor = document.querySelector('.CodeMirror').CodeMirror;" +
	        "editor.setValue(arguments[0]);", code);
	}
	/*public void clickPracticeQuestionslink() {
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.elementToBeClickable(practicequestions)).click();
		
	}
	/*public boolean isPracticequestionsVisible() {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    try {
		        return wait.until(ExpectedConditions.visibilityOf(practicequestions)).isDisplayed();
		    } catch (Exception e) {
		        return false;
		    }
		}*/

	public void clickDropdown() {
		 try {
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].click();", dropdownButton);

		        
		    } catch (Exception e) {
		        System.out.println("ERROR: Dropdown could not be opened: " + e.getMessage());
		        throw e;
		    }
		}
		
	

	public void selectDropdownOption(String moduleName) {
		clickDropdown(); // Make sure dropdown is open

		try { Thread.sleep(1000); } catch (InterruptedException e) {}

	    List<WebElement> items = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	        By.cssSelector("div.dropdown-menu.show a.dropdown-item")
	    ));

	    System.out.println("Dropdown items found: " + items.size());

	    for (WebElement item : items) {
	        if (item.getText().trim().equalsIgnoreCase(moduleName)) {
	            item.click();
	            return;
	        }
	    }

	    throw new RuntimeException("Module not found in dropdown: " + moduleName);
	}}
	
	
	
	
	
