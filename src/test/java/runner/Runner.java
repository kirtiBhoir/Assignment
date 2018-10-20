package runner;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.testng.annotations.Listeners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@Configuration
@ComponentScan(basePackages = {"kirti"})
@Listeners(listener.Listener.class)
@CucumberOptions(features = "feature/flightSearch.feature", glue = { "stepDefinition" })
public class Runner extends AbstractTestNGCucumberTests {
	
}
