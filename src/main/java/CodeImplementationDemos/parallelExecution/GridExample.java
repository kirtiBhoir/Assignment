package CodeImplementationDemos.parallelExecution;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GridExample {
	// ThreadLocal will keep local copy of driver
	public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();

	@BeforeTest
	// Parameter will get browser from testng.xml on which browser test to run
	@Parameters("browser")
	public void beforeClass(String myBrowser) throws MalformedURLException {

		RemoteWebDriver driver = null;

		if (myBrowser.equals("chrome")) {
			DesiredCapabilities capability = new DesiredCapabilities().chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL("http://192.168.0.2:4444/wd/hub"), capability);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (myBrowser.equals("firefox")) {
			DesiredCapabilities capability = new DesiredCapabilities().firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL("http://192.168.0.2"), capability);
			WebDriverManager.chromedriver().setup();
			driver = new FirefoxDriver();
		}

		// setting webdriver
		setWebDriver(driver);

		getDriver().manage().window().maximize();
		getDriver().get("https://www.cleartrip.com/");
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public WebDriver getDriver() {
		return dr.get();
	}

	public void setWebDriver(RemoteWebDriver driver) {
		dr.set(driver);
	}

	@AfterClass
	public void afterClass() {
		getDriver().quit();
		dr.set(null);

	}

}