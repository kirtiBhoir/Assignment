package runner;

import org.junit.runner.RunWith;
import org.testng.annotations.Listeners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@Listeners(listener.Listener.class)
@CucumberOptions(features = "feature", glue = { "stepDefinition" }, plugin = {
		"com.cucumber.listener.ExtentCucumberFormatter:output/report.html" }, monochrome = true)

public class Runner extends AbstractTestNGCucumberTests {

}
