package runner;

import org.testng.annotations.Listeners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@Listeners(listener.Listener.class)
@CucumberOptions(features = "feature/flightSearch.feature", glue = { "stepDefinition" }, plugin = { "pretty",
		"html:target/cucumber-reports/cucumber-pretty", "json:target/cucumber-reports/CucumberTestReport.json" })

public class Runner extends AbstractTestNGCucumberTests {

}
