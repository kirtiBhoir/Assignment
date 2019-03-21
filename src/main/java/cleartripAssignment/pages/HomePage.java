package cleartripAssignment.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import com.cucumber.listener.Reporter;

import cleartripAssignment.common.BasePage;
import cleartripAssignment.messages.HomePageMessages;

@Component
public class HomePage extends BasePage implements cleartripAssignment.messages.HomePageMessages {

	@FindBy(css = "span.cleartripLogo")
	private WebElement logoClearTrip;

	@FindBy(xpath = "//div[@class='searchSummary']")
	private WebElement textSearchResult;

	@FindBy(xpath = "//h1[contains(text(),'Search')]")
	private WebElement textSearchFlights;

	@FindBy(xpath = "//input[@id='SearchBtn']")
	private WebElement btnSearchFlights;

	@FindBy(xpath = "//label[@for='FromTag']/following::input[@id='FromTag']")
	private WebElement dropdownFromCity;

	@FindBy(xpath = "//label[@for='ToTag']/following::input[@id='ToTag']")
	private WebElement dropdownToCity;

	@FindBy(xpath = "//div[@id='homeErrorMessage']")
	private WebElement errorMessageEmptyCity;

	@FindBy(css = "[id=DepartDate]")
	private WebElement departDate;

	@FindBy(css = "[id=ReturnDate]")
	private WebElement returnDate;

	@FindBy(xpath = "//label[@for='Adults']/following::select[@id='Adults']")
	private WebElement dropdownAdult;

	@FindBy(xpath = "//label[@for='Childrens']/following::select[@id='Childrens']")
	private WebElement dropdownChildren;

	@FindBy(xpath = "//label[@for='Infants']/following::select[@id='Infants']")
	private WebElement dropdownInfant;

	@FindBy(css = "[id='OneWay']")
	private WebElement radioBtnOneWay;

	@FindBy(css = "[id='RoundTrip']")
	private WebElement radioBtnRoundTrip;

	public WebElement getLogoClearTrip() {
		return logoClearTrip;
	}

	public void setLogoClearTrip(WebElement logoClearTrip) {
		this.logoClearTrip = logoClearTrip;
	}

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
		
		waitForVisible(logoClearTrip);
		assertTrue(isVisible(logoClearTrip));
		Reporter.addStepLog("Clear trip logo displayed on homepage:"+ isVisible(logoClearTrip) );
		waitForVisible(textSearchFlights);
		assertTrue(isVisible(textSearchFlights));
		Reporter.addStepLog("Text search flight displayed in homepage:"+ isVisible(textSearchFlights) );

	}

	/**
	 * Method to search without entering city name
	 */
	public void searchWithoutCity() {
		waitForVisible(dropdownFromCity);
		Reporter.addStepLog("Drop down from city is displayed:"+ isVisible(dropdownFromCity) );
		waitForVisible(dropdownToCity);
		Reporter.addStepLog("Drop down to city is displayed:"+ isVisible(dropdownToCity) );
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
		Reporter.addStepLog("Button flight search is displayed:"+ isVisible(btnSearchFlights) );
		btnSearchFlights.submit();
		waiforPageToLoad();
	}

	/**
	 * Method to verify error message when flights are searched without cities
	 */
	public void validateErrorMessage() {
		waitForVisible(errorMessageEmptyCity);
		assertTrue(isVisible(errorMessageEmptyCity));
		Reporter.addStepLog("Error message for invalid search is displayed:"+ isVisible(errorMessageEmptyCity) );
		Assert.assertEquals(errorMessageEmptyCity.getText().contains(MESSAGE_EMPTY_CITY), true);
		Reporter.addStepLog("Error message expected is:"+MESSAGE_EMPTY_CITY + "Error message actual is" +errorMessageEmptyCity.getText() );
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
		Reporter.addStepLog("User selected from city"+fromCity );
		waitForVisible(dropdownToCity);
		dropdownToCity.clear();
		dropdownToCity.sendKeys(toCity.toString());
		Reporter.addStepLog("User selected to city"+toCity );
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

			}
		} else {
			waitForVisible(radioBtnRoundTrip);
			if (!radioBtnRoundTrip.isSelected()) {
				radioBtnRoundTrip.click();

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
			departDate.click();
			WebElement date1 = getDate(date);
			date1.click();
			Reporter.addStepLog("user selects" + departDate.getText() + "As Departure date");
		} else {
			waitForVisible(returnDate);
			returnDate.click();
			WebElement date2 = getDate(date);
			date2.click();
			
		}

	}

	public void fillDepartDateDetails(String date) {
		waitForVisible(departDate);
		departDate.click();
		departDate.clear();
		departDate.sendKeys(date);
		Reporter.addStepLog("user selects" + departDate.getText() + "As Departure date");

	}

	public void fillReturDateDetails(String date) {
		waitForVisible(returnDate);
		returnDate.click();
		returnDate.clear();
		returnDate.sendKeys(date);
		Reporter.addStepLog("user selects" + returnDate.getText() + "As Departure date");

	}

}
