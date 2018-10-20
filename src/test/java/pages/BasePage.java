/**
 * 
 */
package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.DriverManager;
import cucumber.api.Scenario;
import utils.FileReader;

/**
 * @author Kirti_Bhoir
 *
 */

public class BasePage {
	private final static Logger logger = Logger.getLogger(BasePage.class);
	Properties properties = new Properties();
	InputStream fileInput = null;

	public static WebDriver driver;
	public FileReader testData = new FileReader();
	public Scenario scenario;

	public void getDriver() throws IOException {
		fileInput = new FileInputStream("./src/test/resources/application.properties");
		properties.load(fileInput);
		driver = DriverManager.getDriver(properties.getProperty("driverName"));
		driver.manage().window().maximize();
	}

	public void launchApplication() throws IOException {
		fileInput = new FileInputStream("./src/test/resources/application.properties");
		properties.load(fileInput);
		driver.get(properties.getProperty("baseUrl"));
	}

	public void closeApplication() {
		driver.close();
	}

	protected boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	protected boolean verifyText(WebElement element, String text) {
		return false;
	}

	protected void waitForPageToLoad() {
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
	}

	protected void waitForVisible(WebElement element) {
		if (!element.isDisplayed()) {
			WebDriverWait wait = new WebDriverWait(driver,90 );
			wait.until(ExpectedConditions.visibilityOf(element));

		}
	}

	protected void selectFromDropDownByIndex(WebElement element, int index) {
		Select dropdown = new Select(element);
		dropdown.selectByIndex(index);
	}
	
	protected void waiforPageToLoad() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String pageLoadStatus = (String) js.executeScript("return document.readyState");
		while (!pageLoadStatus.equals("complete")) {
			logger.info("Page Loaded");
		}
	}
	
	protected void clickUsingActionClass(Keys keyType){
		Actions action = new Actions(driver);
		action.sendKeys(keyType).build().perform();


	}

}
