package stepDefinition;

import java.io.File;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.ApiValidationPage;
import pages.BasePage;
import pages.FlightResultsPage;
import pages.HomePage;

@Component
public class StepDefinition extends BasePage {

	@Autowired
	ApiValidationPage apiValidationpage;

	@Autowired
	HomePage homePage;

	@Autowired
	FlightResultsPage flightResultsPage;

	static AbstractApplicationContext context;

	@Before("@UI")
	public void before(Scenario scenario) throws IOException {
		context = getApplicationContext();
		getDriver();
		launchApplication();
		this.scenario = scenario;
		homePage = context.getBean(HomePage.class);
		flightResultsPage = context.getBean(FlightResultsPage.class);
		homePage.init(driver);
		flightResultsPage.init(driver);

	}

	@Before("@API")
	public void before1() throws IOException {
		context = getApplicationContext();
		apiValidationpage = context.getBean(ApiValidationPage.class);

	}

	@Then("^After navigating to cleartrip website user should see cleartrip homepage$")
	public void validateHomePage() {
		homePage.verifyHomePage();
	}

	@When("^user searches without any values$")
	public void searchWithoutData() throws IOException {
		homePage.searchWithoutCity();
		homePage.searchFlights();
	}

	@Then("^user should see error message$")
	public void validateErrorMessage() throws IOException {
		homePage.validateErrorMessage();

	}

	@When("^user select trip type as \"([^\"]*)\"$")
	public void selectTripType(String tripType) throws IOException {
		homePage.selectTripType(tripType);

	}

	@And("^user enters city as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void fillLocationDetails(String fromCity, String toCity) throws Exception {
		homePage.fillLocationDetails(testData.getData(scenario.getName(), fromCity),
				testData.getData(scenario.getName(), toCity));
	}

	@And("^user wants to \"([^\"]*)\" on \"([^\"]*)\"$")
	public void fillDateDetails(String tripType, String date) throws Exception {
		homePage.fillDateDetails(tripType, testData.getData(scenario.getName(), date));
	}

	@And("^user enters \"([^\"]*)\" along with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void fillPersonCount(String adultCount, String childrenCount, String infantCount) throws Exception {
		homePage.fillPersonCount(testData.getData(scenario.getName(), adultCount),
				testData.getData(scenario.getName(), childrenCount), testData.getData(scenario.getName(), infantCount));
	}

	@And("^user searches for the flights$")
	public void searchFlight() {
		homePage.searchFlights();
	}

	@Then("^user should see flight search results$")
	public void verifySearchResult() {
		flightResultsPage.verifyflightSearchResultsPage();
	}

	@When("^user wants details for \"([^\"]*)\"$")
	public void fetchDetailsForCity(String cityName) throws IOException, ParseException {
		Reporter.addStepLog("City name is" + cityName);
		apiValidationpage.fetchDetailsForCity(cityName);
	}

	@Then("^user validates status details$")
	public void validateStatusDetails() {
		apiValidationpage.validateStatusDetails();
	}

	@Then("^user validates headers$")
	public void validateHeader() {
		apiValidationpage.validateHeader();
	}

	@Then("^user validates city details for \"([^\"]*)\"$")
	public void validateCityDetails(String cityName) throws IOException {
		apiValidationpage.validateCityDetails(cityName);
	}

	@And("^user wants to depart on \"([^\"]*)\"$")
	public void fillDepartDate(String date) throws Exception {
		homePage.fillDepartDateDetails(testData.getData(scenario.getName(), date));
	}

	@And("^user wants to return on \"([^\"]*)\"$")
	public void fillReturnDate(String date) throws Exception {
		homePage.fillReturDateDetails(testData.getData(scenario.getName(), date));
	}

	@After
	public void closeSession() {
		Reporter.loadXMLConfig(new File("config/extent-config.xml"));
		Reporter.setSystemInfo("user", "kirti");
		Reporter.setSystemInfo("os", "Windows 10");
		Reporter.setTestRunnerOutput("Sample test runner output message");
		closeApplication();
	}

}
