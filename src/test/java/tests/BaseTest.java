package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.Helper;

public class BaseTest {
	
	public static WebDriver driver;
	@BeforeSuite  
	@Parameters({"browser"})
	public void startDriver(@Optional("chrome") String browserName) {

		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",  System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		//driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.navigate().to("https://www.phptravels.net/home");
		
		
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void screenShotOnFailuer(ITestResult result) {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Failed! \n"+"Taking A ScreenShot...");
			Helper.captureScreenShot(driver, result.getName());
			
		}
		
		
		
	}


	@AfterSuite
	public void endDriver() {
		driver.quit();
	}


}
