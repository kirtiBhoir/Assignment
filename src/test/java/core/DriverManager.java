package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.BasePage;

public class DriverManager extends BasePage {

	public static WebDriver getDriver(String driverName){
		if(driverName.equalsIgnoreCase("ChromeDriver")){
		System.setProperty("webdriver.chrome.driver", "/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
		}
		return null;
	}
}
  	