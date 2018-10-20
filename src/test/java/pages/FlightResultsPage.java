package pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.testng.Reporter;

import locators.FlightResultPageLoc;
import messages.FlightResultsPageMessages;

@Component
public class FlightResultsPage extends BasePage implements FlightResultPageLoc, FlightResultsPageMessages {

	private final static Logger logger = Logger.getLogger(FlightResultsPage.class);

	@FindBy(xpath = TEXT_RESULT_COUNT)
	private List<WebElement> listSearchResults;

	@FindBy(xpath = TEXT_SEARCH_RESULTS)
	private WebElement textSearchResult;

	@FindBy(xpath = ICON_HOTEL_APP)
	private WebElement iconHotelApp;

	@FindBy(xpath = LIST_FLIGHTS)
	private List<WebElement> listOfFlights;

	public void verifyflightSearchResultsPage() {
		waitForVisible(textSearchResult);
		assertTrue(isDisplayed(textSearchResult));
		assertTrue(!textSearchResult.getText().isEmpty());
		assertTrue(isDisplayed(iconHotelApp));
		waitForPageToLoad();
		assertTrue(listSearchResults.size() > 0);
		waitForPageToLoad();
		verifyFlightList();

	}

	public void verifyFlightList() {
		logger.info("Count of Total flights available:::" + listOfFlights.size());
		Reporter.log("Count of Total flights available:::" + listOfFlights.size());
		Assert.assertThat(listOfFlights.size() > 0, Matchers.equalTo(true));
	}
}