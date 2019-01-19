package com.sas.dvt.VA.managers;

import org.openqa.selenium.WebDriver;

import com.sas.dvt.VA.pageObjects.LoginPage;
import com.sas.dvt.VA.pageObjects.visualanalyticsnext.DataSourcePage;
import com.sas.dvt.VA.pageObjects.visualanalyticsnext.HomePage;
import com.sas.dvt.VA.runners.TestContext;

/**
 * Class  : PageObjectManager
 * PURPOSE: Initializes the objects of all the pages 
 */
public class PageObjectManager {
	WebDriver driver;
	LoginPage loginPage;
	DataSourcePage dataSourcePage;
	HomePage homePage;
	
	public PageObjectManager(WebDriver driver){
		this.driver = driver;
	}
	
	public LoginPage getLoginPage(TestContext testContext){
		return (loginPage == null) ? loginPage = new LoginPage(driver, testContext) : loginPage;
	}
	
	public DataSourcePage getDataSourcePage(TestContext testContext){
		return (dataSourcePage == null) ? dataSourcePage = new DataSourcePage(driver, testContext) : dataSourcePage;
	}
	
	public HomePage getHomePage(TestContext testContext){
		return (homePage == null) ? homePage = new HomePage(driver, testContext) : homePage;
	}
}
