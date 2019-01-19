package com.sas.dvt.VA.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sas.dvt.VA.enums.DriverType;
import com.sas.dvt.VA.runners.ApplicationConfig;

/**
 * Class  : WebDriverManager
 * PURPOSE: Manages all the operations of the web driver 
 */
public class WebDriverManager {

	private WebDriver driver;
	
	/**
     * PURPOSE: Get the web driver
     */
	public WebDriver getDriver() {
		if(driver == null)
			initializeDriver();
		return driver;
	}
	
	public WebDriverWait getWebDriverWait(){
		return new WebDriverWait(driver, 20);
	}
	/**
     * PURPOSE: Initialize the web driver
     */
	private void initializeDriver() {
		DriverType driverType = getDriverType();
		
		switch (driverType) {
			case CHROME :
				driver = initializeChromeDriver();
				break;
			case FIREFOX:
				driver = initializeFirefoxDriver();
				break;
			default:
				System.out.println("Browser: " + driverType + " not supported");
		}
	}
	
	/**
     * PURPOSE: Initialize Chrome driver
     */
	private WebDriver initializeChromeDriver() {
			
		System.out.println("Launching Google Chrome browser...");
		
	    System.setProperty("webdriver.chrome.driver", ApplicationConfig.chromeDriverPath);
		
        driver = new ChromeDriver();

		//Deleting all the cookies of the domain
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Maximize the browser		
		driver.manage().window().maximize();
				
		//Navigate to URL		
		driver.navigate().to();
		
		return driver;
	}

	/**
     * PURPOSE: Initialize Firefox driver
     */
	private static WebDriver initializeFirefoxDriver() {
		System.out.println("Launching Firefox browser...");
		WebDriver driver = new FirefoxDriver();
		
		//Maximize the browser
		driver.manage().window().maximize();
		
		//Deleting all the cookies of the domain
		driver.manage().deleteAllCookies();		

		//Navigate to URL
		//driver.navigate().to();

		return driver;
	}
	
	/**
     * PURPOSE: Get the type of the Driver
     */
	private DriverType getDriverType(){

		switch(ApplicationConfig.browser.toLowerCase()){
			case "chrome":
				return DriverType.CHROME;
			case "firefox":
				return DriverType.FIREFOX;
			case "iexplorer":
				return DriverType.INTERNETEXPLORER;
			case "edge":
				return DriverType.EDGE;
			default:
				throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched");
		}
    }
	
	/**
     * PURPOSE: Close the Driver
     */
	public void closeDriver(){
		driver.quit();
		System.out.println("Browser closed");
	}
}
