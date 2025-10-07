package testrunner1;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepdefinition", "hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/report.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        tags = "@loginPage"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeTest
    @Parameters("browser")
    public void setBrowser(@Optional("chrome") String browser) {
        // Only set system property, DO NOT initialize driver here
        System.setProperty("browser", browser);
        System.out.println("Browser from TestNG XML: " + browser);
    }
}