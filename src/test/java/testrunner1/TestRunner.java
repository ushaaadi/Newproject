package testrunner1;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(plugin = { "pretty", "html:target/cucumber-reports/report.html",
		"json:target/cucumber-reports/report.json",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
        monochrome = true,tags = "@DatastructuresTest",
        features = {
				"src/test/resources/features" }, glue = {"stepdefinition","hooks"})
public class TestRunner extends AbstractTestNGCucumberTests {
//	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}