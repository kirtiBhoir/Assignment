package stepDefinition;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.BasePage;
import pages.FlightResultsPage;
import pages.HomePage;

public class StepDefinition extends BasePage {

	@Autowired
	private HomePage homePage;

	@Autowired
	private FlightResultsPage resultPage;

	Scenario scenario;

	@Before
	public void before(Scenario scenario) throws IOException {
		getDriver();
		launchApplication();
		this.scenario = scenario;
		homePage = PageFactory.initElements(driver, HomePage.class);
		resultPage = PageFactory.initElements(driver, FlightResultsPage.class);
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
		// HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.fillPersonCount(testData.getData(scenario.getName(), adultCount),
				testData.getData(scenario.getName(), childrenCount), testData.getData(scenario.getName(), infantCount));
	}

	@And("^user searches for the flights$")
	public void searchFlight() {
		homePage.searchFlights();
	}

	@Then("^user should see flight search results$")
	public void verifySearchResult() {
		resultPage.verifyflightSearchResultsPage();
	}
}
