package com.sas.dvt.VA.runners;

import com.sas.dvt.VA.managers.PageLocatorManager;
import com.sas.dvt.VA.managers.PageObjectManager;
import com.sas.dvt.VA.managers.WebDriverManager;
import com.sas.dvt.VA.util.ElementActionHandler;

/**
 * Class  : TestContext
 * PURPOSE: Manages the creation of objects  
 */
public class TestContext {

	private WebDriverManager webDriverManager;
	private PageObjectManager pageObjectManager;
	private PageLocatorManager pageLocatorManager;
	private ElementActionHandler elementActionHandler;
	
	public TestContext(){
		webDriverManager = new WebDriverManager();
		pageObjectManager = new PageObjectManager(webDriverManager.getDriver());
		pageLocatorManager = new PageLocatorManager();
		elementActionHandler = new ElementActionHandler(webDriverManager.getDriver());
	}
	
	/**
     * PURPOSE: Get the web driver Manager
     */
	public WebDriverManager getWebDriverManager(){
		return webDriverManager;
	}
	
	/**
     * PURPOSE: Get the PageObject Manager
     */
	public PageObjectManager getPageObjectManager(){
		return pageObjectManager;
	}
	
	/**
     * PURPOSE: Get the PageLocator Manager
     */
	public PageLocatorManager getPageLocatorManager(){
		return pageLocatorManager;
	}
	
	/**
     * PURPOSE: Get the ElementAction Handler
     */
	public ElementActionHandler getElementActionHandler(){
		return elementActionHandler;
	}
}
