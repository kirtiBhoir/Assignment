package pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.Reporter;

import locators.HomePageLoc;
import messages.HomePageMessages;

@Component
public class HomePage extends BasePage implements HomePageLoc, HomePageMessages {

	@FindBy(css = LOGO_CLEAR_TRIP_)
	private WebElement logoClearTrip;

	@FindBy(xpath = TEXT_SEARCH_FLIGHTS)
	private WebElement textSearchFlights;

	@FindBy(xpath = BUTTON_SEARCH_FLIGHTS)
	private WebElement btnSearchFlights;

	@FindBy(xpath = DROPDOWN_FROM_CITY)
	private WebElement dropdownFromCity;

	@FindBy(xpath = DROPDOWN_TO_CITY)
	private WebElement dropdownToCity;

	@FindBy(xpath = ERROR_MESSAGE_EMPTY_CITY)
	private WebElement errorMessageEmptyCity;

	@FindBy(css = DATE_DEPART_DATE)
	private WebElement departDate;

	@FindBy(css = DATE_RETURN_DATE)
	private WebElement returnDate;

	@FindBy(xpath = DROPDOWN_ADULTS)
	private WebElement dropdownAdult;

	@FindBy(xpath = DROPDOWN_CHILDREN)
	private WebElement dropdownChildren;

	@FindBy(xpath = DROPDOWN_INFANT)
	private WebElement dropdownInfant;

	@FindBy(css = RADIO_BTN_ONE_WAY_TRIP)
	private WebElement radioBtnOneWay;

	@FindBy(css = RADIO_BTN_ROUND_TRIP)
	private WebElement radioBtnRoundTrip;

	public void verifyHomePage() {

		assertTrue(isDisplayed(logoClearTrip));
		assertTrue(isDisplayed(textSearchFlights));
	}

	public void searchWithoutCity() {
		if (!dropdownFromCity.getText().isEmpty()) {
			dropdownFromCity.clear();
		}
		if (!dropdownToCity.getText().isEmpty()) {
			dropdownToCity.clear();
		}
	}

	public void searchFlights() {
		waitForVisible(btnSearchFlights);
		btnSearchFlights.submit();
		waitForPageToLoad();
	}

	public void validateErrorMessage() {
		assertTrue(isDisplayed(errorMessageEmptyCity));
		Assert.assertEquals(errorMessageEmptyCity.getText().contains(MESSAGE_EMPTY_CITY), true);
	}

	public void fillLocationDetails(String fromCity, String toCity) {
		waitForVisible(dropdownFromCity);
		dropdownFromCity.clear();
		dropdownFromCity.sendKeys(fromCity.toString());
		clickUsingActionClass(Keys.ARROW_DOWN);
		waitForVisible(dropdownToCity);
		dropdownToCity.clear();
		dropdownToCity.sendKeys(toCity.toString());
		clickUsingActionClass(Keys.ARROW_DOWN);

	}

	public void fillPersonCount(String adultCount, String childrenCount, String infantCount) {
		waitForVisible(dropdownAdult);
		selectFromDropDownByIndex(dropdownAdult, Integer.parseInt(adultCount));
		selectFromDropDownByIndex(dropdownChildren, Integer.parseInt(childrenCount));
		selectFromDropDownByIndex(dropdownInfant, Integer.parseInt(infantCount));
	}

	public void selectTripType(String tripType) {
		if (tripType.equalsIgnoreCase("one way")) {
			waitForVisible(radioBtnOneWay);
			if (!radioBtnOneWay.isSelected()) {
				radioBtnOneWay.click();
				waitForPageToLoad();
			}
		} else {
			waitForVisible(radioBtnRoundTrip);
			if (!radioBtnRoundTrip.isSelected()) {
				radioBtnRoundTrip.click();
				waitForPageToLoad();
			}
		}

	}

	public void fillDateDetails(String tripType, String date) {
		if (tripType.equalsIgnoreCase("depart")) {
			waitForVisible(departDate);
			departDate.clear();
			departDate.sendKeys(date);
			Reporter.log("user selects" + departDate + "As Departure date");
		} else {
			waitForVisible(returnDate);
			returnDate.clear();
			returnDate.sendKeys(date);
			Reporter.log("user selects" + returnDate + "As Return date");
		}
		textSearchFlights.click();

	}

}
