package testrunner1;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.ConfigReader;

@CucumberOptions(plugin = { "pretty", "html:target/cucumber-reports/report.html",
		"json:target/cucumber-reports/report.json",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
        monochrome = true,//tags = "@DatastructuresTest",
        features = {
				"src/test/resources/features/Datastructures" }, glue = {"stepdefinition","hooks"})
public class TestRunner extends AbstractTestNGCucumberTests {
	@BeforeTest
	@Parameters({"browser"})
	public void defineBrowser(String browser) throws Throwable {
		ConfigReader.setBrowserType(browser);
		//System.out.println(browser);
		// DriverFactory.initializeDriver();
	}
//	@Override
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}