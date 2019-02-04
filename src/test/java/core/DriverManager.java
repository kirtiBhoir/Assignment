package core;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.BasePage;

public class DriverManager extends BasePage {

	/**
	 * Method to create browser instance
	 * 
	 * @param driverName
	 * @return
	 */
	public synchronized WebDriver getDriver(String driverName) {

		if (driverName.equalsIgnoreCase("ChromeDriver")) {
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.setExperimentalOption("prefs", prefs);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			return driver;
		} else if (driverName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			return driver;
		}
		return null;
	}
}
