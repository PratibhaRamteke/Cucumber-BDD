package com.sas.dvt.VA.pageObjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sas.dvt.VA.managers.PageLocatorManager;
import com.sas.dvt.VA.runners.ApplicationConfig;
import com.sas.dvt.VA.runners.TestContext;
import com.sas.dvt.VA.util.ElementActionHandler;

/**
 * Class  : LoginPage
 * PURPOSE: Manages all the actions of the Login Page
 */
public class LoginPage {
	WebDriver driver;
	WebDriverWait webDriverWait;
	Properties loginPageProperties;
	PageLocatorManager pageLocatorManager;
	ElementActionHandler elementActionHandler;
	
	public LoginPage(WebDriver driver, TestContext testContext){
		this.driver = driver;
		webDriverWait = testContext.getWebDriverManager().getWebDriverWait();
		
		elementActionHandler = testContext.getElementActionHandler();
		pageLocatorManager = testContext.getPageLocatorManager();
		loginPageProperties = pageLocatorManager.getPageProperties("LoginPage.properties",false);
	}
	
	//Navigate to application URL	
	public void navigateToURL(){
		driver.navigate().to(ApplicationConfig.applicationURL);
	}
	
	public void loginToApplication(){
		//Enter Username
		enterUserName();
		
		//Enter Password
		enterPassword();
		
		//Click signIn button
		clickSignInButton();
		
		//Handle pop-up
		handleGroupsPopUp();
		
		//Set the application-specific frame
		setFrame();
		
		//Handle unsaved changes Pop-up
		handleUnsavedChangesPopUp();
		
		//Handle welcome setup message
		//handleWelcomeSetupMessage();
		
		System.out.println("Successfully logged into application");
	}
	
	private void enterUserName(){
		WebElement userTextBox = driver.findElement(
				pageLocatorManager.getLocatorValue("usernameTextField", loginPageProperties));
		elementActionHandler.setText(userTextBox, ApplicationConfig.user);			
	}
	
	private void enterPassword(){
		WebElement passwordTextBox = driver.findElement(
				pageLocatorManager.getLocatorValue("passwordTextField", loginPageProperties));
		elementActionHandler.setText(passwordTextBox, ApplicationConfig.password);			
	}
	
	private void clickSignInButton(){
		WebElement signInButton = driver.findElement(
				pageLocatorManager.getLocatorValue("signInButton", loginPageProperties));
		elementActionHandler.clickElement(signInButton);
	}
	
	private void handleGroupsPopUp(){
		try{
			By adminButton = pageLocatorManager.getLocatorValue("sasAdminButton", loginPageProperties);
			webDriverWait.until(ExpectedConditions.elementToBeClickable(adminButton));
			elementActionHandler.clickElement(driver.findElement(adminButton));
		}catch(Exception exception){
			
		}
	}
	
	private void setFrame(){
		webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
				pageLocatorManager.getLocatorValue(
						ApplicationConfig.applicationName.toUpperCase() + "FRAME", loginPageProperties)));
	}
	
	private void handleWelcomeSetupMessage(){
		try{
			By skipButton = pageLocatorManager.getLocatorValue(
					"welcomeMessageSkipSetupButton", loginPageProperties);
			webDriverWait.until(ExpectedConditions.elementToBeClickable(skipButton));
			elementActionHandler.clickElement(driver.findElement(skipButton));
		}catch(Exception exception){
			
		}
	}
	
	private void handleUnsavedChangesPopUp(){
		try {
			By noButton = pageLocatorManager.getLocatorValue(
					"skipUnsavedChangesButton", loginPageProperties);
			webDriverWait.until(ExpectedConditions.elementToBeClickable(noButton));
			elementActionHandler.clickElement(driver.findElement(noButton));
		}catch(Exception exception){
			//logger.error(exception.toString());
		}
	}
}
