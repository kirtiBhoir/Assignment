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

import com.cucumber.listener.Reporter;

@Component
public class FlightResultsPage extends BasePage {
	@FindBy(xpath = "//strong[@class='resultsCount']")
	private List<WebElement> listSearchResults;

	@FindBy(xpath = "//div[@class='searchSummary']")
	private WebElement textSearchResult;

	@FindBy(xpath = "//li[@class='hotelApp']")
	private WebElement iconHotelApp;

	@FindBy(xpath = "//*[contains(@class,'listItem')]")
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
		waitForVisible(textSearchResult);
		assertTrue(isVisible(textSearchResult));
		Reporter.addStepLog("text search result is displayed" + isVisible(textSearchResult));
		assertTrue(!textSearchResult.getText().isEmpty());
		Reporter.addStepLog("text search result is:--" + textSearchResult.getText());
		waitForVisible(iconHotelApp);
		assertTrue(isVisible(iconHotelApp));
		Reporter.addStepLog("icon hotel app is displayed" + isVisible(iconHotelApp));
		waitForVisible(listSearchResults.get(1));
		assertTrue(listSearchResults.size() > 0);
		Reporter.addStepLog("Result list size" + listSearchResults.size());
		verifyFlightList();

	}

	/**
	 * Method to verify available flights list using java 8 stream
	 */
	public void verifyFlightList() {
		waitForVisible(listOfFlights.get(1));
		Assert.assertThat(listOfFlights.size() > 0, Matchers.equalTo(true));
		Reporter.addStepLog("Result list size" + listOfFlights.size());
		waiforLoaderToDismiss();
		List<String> priceList = listOfFlights.stream().map(element -> element.getAttribute("data-price"))
				.collect(Collectors.toList());
		Reporter.addStepLog("price list size" + priceList.size());

	}

}