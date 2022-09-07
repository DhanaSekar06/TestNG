package com.automation.setup;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;

import com.automation.utilities.Utiles;

public class ProjectSetup {
	public static WebDriver driver;
	protected static String PracticePortal_URL = "https://www.gaana.com/";

	@BeforeTest(alwaysRun = true)
	public void setupWebDriver() {

		browserLaunch("chrome");

	}

	@AfterSuite(alwaysRun = true)
	public static void getSuiteName() {
		driver.quit();
		System.out.println("after suite run");

	}

	public void browserLaunch(@Optional("chrome") String browser) {

		switch (browser) {

		case "firefox":

			System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			break;

		case "chrome":

			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "edge":

			System.setProperty("webdriver.edge.driver", "Drivers/msedgedriver.exe");
			driver = new EdgeDriver();
			break;

		case "opera":
			System.setProperty("webdriver.opera.driver", "Drivers/operadriver.exe");
			driver = new OperaDriver();
			break;

		default:
			throw new RuntimeException("Browser type unsupported");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utiles.implicityWaitTimeInSec));
		driver.manage().window().maximize();
		driver.get(PracticePortal_URL);

	}

}
