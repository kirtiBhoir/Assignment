package pages;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import locators.FlightResultPageLoc;

@Component
public class FlightResultsPage extends BasePage implements FlightResultPageLoc {
	@FindBy(xpath = TEXT_RESULT_COUNT)
	private List<WebElement> listSearchResults;

	@FindBy(xpath = TEXT_SEARCH_RESULTS)
	private WebElement textSearchResult;

	@FindBy(xpath = ICON_HOTEL_APP)
	private WebElement iconHotelApp;

	@FindBy(xpath = LIST_FLIGHTS)
	private List<WebElement> listOfFlights;

	/**
	 * Method to initialize page object model
	 */
	public void init(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method to verify flight search result page
	 */
	public void verifyflightSearchResultsPage() {
		waiforPageToLoad();
		waitForVisible(textSearchResult);
		assertTrue(isVisible(textSearchResult));
		assertTrue(!textSearchResult.getText().isEmpty());
		assertTrue(isVisible(iconHotelApp));
		assertTrue(listSearchResults.size() > 0);
		verifyFlightList();

	}

	/**
	 * Method to verify available flights list using java 8 stream
	 */
	public void verifyFlightList() {
		waiforPageToLoad();
		waiforLoaderToDismiss();
		Assert.assertThat(listOfFlights.size() > 0, Matchers.equalTo(true));
		listOfFlights.stream().filter(element -> element.getText().equalsIgnoreCase("")).collect(Collectors.toList());
		List<String> ResultList = listOfFlights.stream().map(element -> element.getText()).collect(Collectors.toList());
		Assert.assertThat(ResultList.size() > 0, Matchers.equalTo(true));

	}

}