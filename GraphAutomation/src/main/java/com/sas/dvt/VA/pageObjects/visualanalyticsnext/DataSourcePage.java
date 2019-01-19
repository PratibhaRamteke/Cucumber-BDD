package com.sas.dvt.VA.pageObjects.visualanalyticsnext;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sas.dvt.VA.managers.PageLocatorManager;
import com.sas.dvt.VA.runners.TestContext;
import com.sas.dvt.VA.util.ElementActionHandler;

public class DataSourcePage {

	WebDriver driver;
	WebDriverWait webDriverWait;
	
	Properties dataSourcePageProperties;
	PageLocatorManager pageLocatorManager;
	ElementActionHandler elementActionHandler;
	
	public DataSourcePage(WebDriver driver, TestContext testContext){
		this.driver = driver;
		webDriverWait = testContext.getWebDriverManager().getWebDriverWait();
		
		elementActionHandler = testContext.getElementActionHandler();
		pageLocatorManager = testContext.getPageLocatorManager();
		dataSourcePageProperties = pageLocatorManager.getPageProperties("DataSourcePage.properties", true);
	}
	
	/**
	 * Purpose: Click on Data button from "Get started" pop-up
	 */
	public void clickDataButton() {
		By dataButton = pageLocatorManager.getLocatorValue("dataButton", dataSourcePageProperties);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(dataButton));
		elementActionHandler.clickElement(driver.findElement(dataButton));
	}
	
	/**
	 * Purpose: Select a data source
	 * @param datasetName : Name of the dataSet 
	 */
	public void selectDataSource(String dataSetName) {
		//Search DataSource 
		searchDataSet(dataSetName);
		
		//Check if DataSet is Present 
		if(isDataSetPresent(dataSetName))
		{
			//Select DataSet 
			selectDataSet();
			
			//Click on OK Button
			clickOKButton();
		}
	}
	
	private void searchDataSet(String datasetName){
		By searchField = pageLocatorManager.getLocatorValue("dataSourceSearchField", dataSourcePageProperties);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(searchField));
		elementActionHandler.setText(driver.findElement(searchField), datasetName);
	}
	
	private void selectDataSet(){
		By dataSet = pageLocatorManager.getLocatorValue("datasetElement", dataSourcePageProperties);
		try{
			webDriverWait.until(ExpectedConditions.elementToBeClickable(dataSet));
			elementActionHandler.clickElement(driver.findElement(dataSet));
		}catch(Exception exception){
			webDriverWait.until(ExpectedConditions.elementToBeClickable(dataSet));
			elementActionHandler.clickElement(driver.findElement(dataSet));
		}
	}
	
	private void clickOKButton(){
		By okButton = pageLocatorManager.getLocatorValue("okDataSelectionButton", dataSourcePageProperties);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(okButton));
		elementActionHandler.clickElement(driver.findElement(okButton));
	}
	
	public boolean isDataSetPresent(String datasetName){			
		boolean flag=true;

		try {
			By messageText = pageLocatorManager.getLocatorValue("noItemPresentText", dataSourcePageProperties);
			if(driver.findElement(messageText).isDisplayed()){
				System.out.println("Data Source: "+ datasetName +" is not present");
				flag = false;
			}
		}catch(Exception exception) {
			//logger.info("DataSet: "+ datasetName + " is present");
		}

		return flag;
	}
}
