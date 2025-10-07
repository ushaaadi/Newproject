package stepdefinition;

import Base.Driverfactory;
import org.openqa.selenium.WebDriver;

public class BaseSteps {
	protected WebDriver driver = Driverfactory.getDriver();
}
