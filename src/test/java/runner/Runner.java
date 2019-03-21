package runner;

import org.junit.runner.RunWith;
import org.testng.annotations.Listeners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@Listeners(cleartripAssignment.listener.Listener.class)
@CucumberOptions(features = "src/test/resources/feature", glue = { "stepDefinition" }, plugin = {
		"com.cucumber.listener.ExtentCucumberFormatter:output/report.html" ,"pretty", "html:target/cucumber-reports/cucumber-pretty","json:target/cucumber-reports/CucumberTestReport.json"}, monochrome = true)

public class Runner extends AbstractTestNGCucumberTests {

}


