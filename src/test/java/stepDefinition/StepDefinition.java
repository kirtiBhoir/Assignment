package stepDefinition;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

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
	private ApiValidationPage apiValidationpage;

	@Autowired
	HomePage homePage;

	@Autowired
	FlightResultsPage FlightResultsPage;

	Scenario scenario;

	AbstractApplicationContext context;

	@Before
	public void before(Scenario scenario) throws IOException {
		context = getApplicationContext();
		getDriver();
		launchApplication();
		this.scenario = scenario;
		homePage = context.getBean(HomePage.class);
		System.out.println("homePage:" + homePage);
		FlightResultsPage = context.getBean(FlightResultsPage.class);
		System.out.println("FlightResultsPage:" + FlightResultsPage);
		apiValidationpage = context.getBean(ApiValidationPage.class);
		System.out.println("apiValidationpage:" + apiValidationpage);
		homePage.init(driver);
		FlightResultsPage.init(driver);

	}

	@Then("^After navigating to cleartrip website user should see cleartrip homepage$")
	public void validateHomePage() {
		System.out.println("homePage:" + homePage);
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

	@Then("^user select trip type as \"([^\"]*)\"$")
	public void selectTripType(String tripType) throws IOException {
		homePage.selectTripType(tripType);

	}

	@When("^user enters city as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void fillLocationDetails(String fromCity, String toCity) throws Exception {
		homePage.fillLocationDetails(testData.getData(scenario.getName(), fromCity),
				testData.getData(scenario.getName(), toCity));
	}

	@When("^user wants to \"([^\"]*)\" on \"([^\"]*)\"$")
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
		FlightResultsPage.verifyflightSearchResultsPage();
	}

	@Then("^user wants details for \"([^\"]*)\"$")
	public void fetchDetailsForCity(String cityName) throws IOException {
		System.out.println("city name" + cityName);
		apiValidationpage.fetchDetailsForCity(testData.getData(scenario.getName(), cityName));
	}

	@Then("^user validates status details$")
	public void validateStatusDetails() {
		apiValidationpage.validateStatusDetails();
	}

	@And("^user validates headers$")
	public void validateHeader() {
		apiValidationpage.validateHeader();
	}

	@Then("^user validates city details for \"([^\"]*)\"$")
	public void validateCityDetails(String cityName) throws IOException {
		apiValidationpage.validateCityDetails(testData.getData(scenario.getName(), cityName));
	}

	@After
	public void closeSession() {
		closeApplication();
	}

}
