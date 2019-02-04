package parallelExecution;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.BasePage;

public class MultiBrowser extends BasePage{

	@FindBy(xpath = "//label[@for='FromTag']/following::input[@id='FromTag']")
	private WebElement dropdownFromCity;

	@FindBy(xpath = "//label[@for='ToTag']/following::input[@id='ToTag']")
	private WebElement dropdownToCity;

	@FindBy(xpath = "//div[@id='homeErrorMessage']")
	private WebElement errorMessageEmptyCity;

	@FindBy(xpath = "//input[@id='SearchBtn']")
	private WebElement btnSearchFlights;

	public WebDriver driver;
	public String MESSAGE_EMPTY_CITY = "Sorry, but we can't continue until you fix everything that's marked in RED";

	@Parameters("browser")
	@BeforeClass

	public void beforeTest(String browser) {

		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("chrome")) {
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.setExperimentalOption("prefs", prefs);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

		} else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();

		}

		driver.manage().window().maximize();
		driver.get("https://www.cleartrip.com/");

	}

	@Test
	public void validateEmptySearch() throws InterruptedException {

		dropdownFromCity.clear();
		waitForVisible(dropdownToCity);
		dropdownToCity.clear();
		waitForVisible(btnSearchFlights);
		btnSearchFlights.click();
		waitForVisible(errorMessageEmptyCity);
		assertTrue(errorMessageEmptyCity.isDisplayed());
		Assert.assertEquals(errorMessageEmptyCity.getText().contains(MESSAGE_EMPTY_CITY), true);

	}

	@AfterClass
	public void afterTest() {

		driver.quit();

	}
}
