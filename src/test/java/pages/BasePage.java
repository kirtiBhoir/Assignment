/**
 * 
 */
package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.testng.Reporter;

import core.AppConfig;
import core.DriverManager;
import cucumber.api.Scenario;
import utils.ExcelReaderUtilityManager;
import utils.FileReader;

public class BasePage extends ExcelReaderUtilityManager {
	protected final static Logger logger = Logger.getLogger(BasePage.class);
	Properties properties = new Properties();
	InputStream fileInput = null;

	public static WebDriver driver;
	public FileReader testData = new FileReader();
	public Scenario scenario;
	protected static String reportConfigPath = System.getProperty("user.dir") + "\\cofig\\extent-config.xml";

	/**
	 * Method to get application context
	 * 
	 * @return
	 */
	public AbstractApplicationContext getApplicationContext() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		return context;
	}

	/**
	 * functional interface to convert datatype to integer
	 * 
	 * @author Kirti_Bhoir
	 *
	 */
	interface ConvertDataType {
		int operation(String input);
	}

	/**
	 * Method to convert data type
	 * 
	 * @param data
	 * @param obj
	 * @return
	 */
	protected int operate(String data, ConvertDataType obj) {
		return obj.operation(data);
	}

	/**
	 * Method to read browser value and call to method for creating browser
	 * 
	 * @throws IOException
	 */
	public void getDriver() throws IOException {
		DriverManager d = new DriverManager();
		try {
			fileInput = new FileInputStream("./src/test/resources/driver.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			properties.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		driver = d.getDriver(properties.getProperty("driverName"));
		driver.manage().window().maximize();
	}

	/**
	 * Method to launch application and hit base URL
	 * 
	 * @throws IOException
	 */
	public void launchApplication() throws IOException {
		readPropertiesFile();
		driver.get(properties.getProperty("baseUrl"));
	}

	/**
	 * Method to close browser instance
	 */
	public void closeApplication() {
		try {
			if (driver != null) {
				driver.quit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			Reporter.log("No active driver available to close");
			System.exit(1);
		}
	}

	/**
	 * Method to check visibility of web element
	 * 
	 * @param element
	 * @return
	 */
	protected boolean isVisible(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
			return false;

		}

	}

	/**
	 * Method to wait for element to be visible on page
	 * 
	 * @param element
	 */
	protected void waitForVisible(WebElement element) {
		if (!element.isDisplayed()) {
			WebDriverWait wait = new WebDriverWait(driver, 200);
			wait.until(ExpectedConditions.visibilityOf(element));

		}
	}

	/**
	 * Method to wait for element to be invisible on page
	 * 
	 * @param element
	 */
	protected void waitForNotVisible(WebElement element) {
		if (element.isDisplayed()) {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOf(element));

		}
	}

	/**
	 * Method to select option from dropdown by index
	 * 
	 * @param element
	 * @param index
	 */
	protected void selectFromDropDownByIndex(WebElement element, int index) {
		Select dropdown = new Select(element);
		dropdown.selectByIndex(index);
	}

	/**
	 * Method to wait for complete page to load
	 */
	protected void waiforPageToLoad() {
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String pageLoadStatus = (String) js.executeScript("return document.readyState");
		while (!pageLoadStatus.equals("complete")) {
			logger.info("Page Loaded");
		}
	}

	/**
	 * Method to wait for loader to get dismiss
	 */
	protected void waiforLoaderToDismiss() {
		try {
			driver.navigate().refresh();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

	/**
	 * Method to click using Action class
	 * 
	 * @param keyType
	 */
	protected void clickUsingActionClass(Keys keyType) {
		Actions action = new Actions(driver);
		action.sendKeys(keyType).build().perform();

	}

	/**
	 * Method to get screen shot
	 * 
	 * @param methodName
	 * @throws IOException
	 */
	protected void takeScreenShot(String methodName) throws IOException {
		File file = new File("testresults");
		file.getAbsolutePath();
		String screenShotName = methodName + ".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File desFile = new File("./testresults/" + screenShotName);
		FileUtils.copyFile(scrFile, desFile);
	}

	/**
	 * Method to load properties file
	 */
	protected void readPropertiesFile() {
		try {
			fileInput = new FileInputStream("./src/test/resources/application.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			properties.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Method to get value from properties file ,when key is provided
	 * 
	 * @param key
	 * @return
	 */
	protected String getValue(String key) {
		readPropertiesFile();
		String value = properties.getProperty(key);
		return value;
	}

	private final String selectDate = "//td[@data-month='%s' and @data-year='%s' ]/a[text()='%s']";

	public WebElement selectDate(int month, String year, String day) {
		WebElement element = driver.findElement(By.xpath(String.format(selectDate, month, year, day)));
		return element;
	}

	/**
	 * Method to create locator from user input date
	 */
	protected WebElement getDate(String date) {

		String dateArray[] = date.split(" ");
		for (String s : dateArray) {
			System.out.println(s);
		}
		int month = Integer.parseInt(dateArray[1]);
		String year = dateArray[2];
		String day = dateArray[0];
		WebElement date1 = selectDate(month - 1, year, day);
		return date1;

	}

	protected String getReportConfigPath() {
		if (reportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException(
					"Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
	}
}
