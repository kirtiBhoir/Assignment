package pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
	/**
	 * Method to initialize page object model
	 */
	public void init(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method to verify clear trip home page
	 */
	public void verifyHomePage() {
		
		assertTrue(isVisible(logoClearTrip));
		assertTrue(isVisible(textSearchFlights));
	}

	/**
	 * Method to search without entering city name
	 */
	public void searchWithoutCity() {
		if (!dropdownFromCity.getText().isEmpty()) {
			dropdownFromCity.clear();
		}
		if (!dropdownToCity.getText().isEmpty()) {
			dropdownToCity.clear();
		}
	}

	/**
	 * Method to search flights
	 */
	public void searchFlights() {
		waitForVisible(btnSearchFlights);
		btnSearchFlights.submit();
		waiforPageToLoad();
	}

	/**
	 * Method to verify error message when flights are searched without cities
	 */
	public void validateErrorMessage() {
		assertTrue(isVisible(errorMessageEmptyCity));
		Assert.assertEquals(errorMessageEmptyCity.getText().contains(MESSAGE_EMPTY_CITY), true);
	}

	/**
	 * Method to enter location details for flight search
	 * 
	 * @param fromCity
	 * @param toCity
	 */
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

	/**
	 * Method to enter person details for flight search
	 * 
	 * @param adultCount
	 * @param childrenCount
	 * @param infantCount
	 */
	public void fillPersonCount(String adultCount, String childrenCount, String infantCount) {
		waitForVisible(dropdownAdult);
		waitForVisible(dropdownChildren);
		waitForVisible(dropdownInfant);

		// provided implementation to functional interface
		ConvertDataType convertToInteger = (String value) -> Integer.parseInt(value);

		// call to functional interface using lambda expression
		selectFromDropDownByIndex(dropdownAdult, operate(adultCount, convertToInteger));
		selectFromDropDownByIndex(dropdownChildren, operate(childrenCount, convertToInteger));
		selectFromDropDownByIndex(dropdownInfant, operate(infantCount, convertToInteger));

	}

	/**
	 * Method to select trip type
	 * 
	 * @param tripType
	 */
	public void selectTripType(String tripType) {
		if (tripType.equalsIgnoreCase("one way")) {
			waitForVisible(radioBtnOneWay);
			if (!radioBtnOneWay.isSelected()) {
				radioBtnOneWay.click();
				waiforPageToLoad();
			}
		} else {
			waitForVisible(radioBtnRoundTrip);
			if (!radioBtnRoundTrip.isSelected()) {
				radioBtnRoundTrip.click();
				waiforPageToLoad();
			}
		}

	}

	/**
	 * Method to select departure and arrival date
	 * 
	 * @param tripType
	 * @param date
	 */
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
